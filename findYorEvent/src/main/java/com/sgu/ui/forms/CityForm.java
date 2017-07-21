package com.sgu.ui.forms;

import com.sgu.entity.City;
import com.sgu.entity.Country;
import com.sgu.services.CityCountryService;
import com.sgu.ui.CityUI;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * Created by admin on 09.07.2017.
 */
public class CityForm extends FormLayout {

    private TextField cityName = new TextField("Название города");
    private ComboBox<Country> comboBox = new ComboBox<>("Название страны");
    private Button save = new Button("Сохранить");
    private Button delete = new Button("Удалить");

    private CityCountryService service = CityCountryService.getInstance();
    private CityUI cityUI;
    private City city;
    private Country countryCity;
    private Binder<City> binderCity = new Binder<>(City.class);
    public CityForm(CityUI cityUI){
        this.cityUI = cityUI;
        comboBox.setEmptySelectionAllowed(false);
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);

        addComponents(cityName,comboBox ,buttons);


        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binderCity.bindInstanceFields(this);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
        updateListCountry();
    }

    public void setCity(City city){
        this.city = city;
        this.countryCity = city.getCountryCity();
        binderCity.setBean(city);
        comboBox.setSelectedItem(countryCity);
        delete.setVisible(true);
        setVisible(true);
        cityName.selectAll();

    }

    private void delete() {

        service.deleteCity(city);
        cityUI.updateLists();
        setVisible(false);


    }

    private void save() {
        if (comboBox.getValue()==null){
            Notification.show("Пожалуйста выберете страну!");
        }
        else {
            service.addCity(city, comboBox.getValue());
            cityUI.updateLists();
            //setVisible(false);
            //comboBox.setValue(null);
        }
        //comboBox.setValue(null);
        comboBox.setSelectedItem(null);
        System.out.println("SAVE");
        System.out.println(comboBox.getValue());
        comboBox.clear();
    }

    public void updateListCountry(){
        List<Country> countries;
        countries = service.getAllCountyes();
        comboBox.setItems(countries);
        //comboBox.setValue(null);
        System.out.println("UPDATES!!!");
        System.out.println(comboBox.getValue());

    }


}
