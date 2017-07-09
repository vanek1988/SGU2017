package com.sgu.ui;

import com.sgu.entity.City;
import com.sgu.entity.Country;
import com.sgu.services.CityCountryService;
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
 * Created by admin on 08.07.2017.
 */

@SpringUI
@Theme("valo")
@Title("Главная страница")
//@PreserveOnRefresh
public class MainUI extends UI {

    private CityCountryService service = CityCountryService.getInstance();
    private Grid<Country> countryGrid = new Grid<>(Country.class);
    private Grid<City> cityGrid  = new Grid<>(City.class);
    private TextField filterText = new TextField();
    private CountryForm countryForm = new CountryForm(this);

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();

        filterText.setPlaceholder("поиск по имени");
        filterText.addValueChangeListener(e -> updateLists());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Удаление поиска");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());


        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addCountryBtn = new Button("Добавить страну");
        addCountryBtn.addClickListener(e->{
            countryGrid.asSingleSelect().clear();
            countryForm.setCountry(new Country());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering,addCountryBtn);

        countryGrid.setColumns("countryName");
        cityGrid.setColumns("countryCity","cityName");




        HorizontalLayout main = new HorizontalLayout(countryGrid, countryForm);
        main.setSizeFull();
        countryGrid.setSizeFull();
        main.setExpandRatio(countryGrid, 1);

        layout.addComponents(toolbar, main);




        updateLists();
        setContent(layout);

        countryForm.setVisible(false);
        countryGrid.asSingleSelect().addValueChangeListener(event-> {
            if (event.getValue() == null){
                countryForm.setVisible(false);
            }
            else{
                countryForm.setCountry(event.getValue());
            }
        });

    }

    public void updateLists(){
        List<City> cities = service.getAllCityes();
        List<Country> countries;
        if (filterText.isEmpty())
            countries= service.getAllCountyes();
        else {
            //countries= service.getAllCountyes();
            //System.out.println(filterText.getValue());
            countries= service.getAllCountyes(filterText.getValue());
        }
        countryGrid.setItems(countries);
        cityGrid.setItems(cities);
    }

}
