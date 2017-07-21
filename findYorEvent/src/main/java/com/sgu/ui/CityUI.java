package com.sgu.ui;

import com.sgu.entity.City;
import com.sgu.entity.Country;
import com.sgu.services.CityCountryService;
import com.sgu.ui.forms.CityForm;
import com.sgu.ui.forms.CountryForm;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * Created by admin on 19.07.2017.
 */

@SpringUI(path = "city")
@Theme("valo")
@Title("Города")
public class CityUI  extends UI{

    private CityCountryService service = CityCountryService.getInstance();
    private Grid<City> cityGrid  = new Grid<>(City.class);
    private TextField filterText = new TextField();
    private CityForm cityForm = new CityForm(this);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        filterText.setPlaceholder("поиск по городу");
        filterText.addValueChangeListener(e -> updateLists());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Удаление поиска");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());


        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addCityBtn = new Button("Добавить город");
        addCityBtn.addClickListener(e->{
            cityGrid.asSingleSelect().clear();
            cityForm.setCity(new City());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering,addCityBtn);

        cityGrid.setColumns("countryCity","cityName");




        HorizontalLayout main = new HorizontalLayout(cityGrid, cityForm);
        main.setSizeFull();
        cityGrid.setSizeFull();
        main.setExpandRatio(cityGrid, 1);

        layout.addComponents(toolbar, main);




        updateLists();
        setContent(layout);

        cityForm.setVisible(false);
        cityGrid.asSingleSelect().addValueChangeListener(event-> {
            if (event.getValue() == null){
                cityForm.setVisible(false);
            }
            else{
                cityForm.setCity(event.getValue());

            }
        });

    }

    public void updateLists(){
        List<City> cities;

        if (filterText.isEmpty())
            cities = service.getAllCityes();
        else {

            cities = service.getAllCityes(filterText.getValue());
        }
        cityGrid.setItems(cities);
        cityForm.updateListCountry();

    }
}
