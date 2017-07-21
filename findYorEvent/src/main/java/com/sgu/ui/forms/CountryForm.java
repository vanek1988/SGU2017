package com.sgu.ui.forms;

/**
 * Created by admin on 09.07.2017.
 */

import com.sgu.entity.Country;
import com.sgu.services.CityCountryService;
import com.sgu.ui.MainUI;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class CountryForm extends FormLayout {

    private TextField countryName = new TextField("Название страны");
    private Button save = new Button("Сохранить");
    private Button delete = new Button("Удалить");

    private CityCountryService service = CityCountryService.getInstance();
    private Country country;
    private MainUI mainUI;
    private Binder<Country> binder = new Binder<>(Country.class);

    public CountryForm( MainUI mainUI) {
        this.mainUI =  mainUI;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(countryName, buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(KeyCode.ENTER);

        binder.bindInstanceFields(this);
        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
    }
    public void setCountry(Country country) {
        this.country = country;
        binder.setBean(country);

        delete.setVisible(true);
        setVisible(true);
        countryName.selectAll();

    }

    private void delete() {

        service.deleteCountry(country);
        mainUI.updateLists();

        setVisible(false);
    }

    private void save() {

        service.addCountry(country);
        mainUI.updateLists();
        setVisible(false);
    }



}
