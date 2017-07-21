package com.sgu.ui.forms;

import com.sgu.entity.City;
import com.sgu.entity.Country;
import com.sgu.entity.Event;
import com.sgu.entity.User;
import com.sgu.services.CityCountryService;
import com.sgu.services.EventService;
import com.sgu.ui.EventUI;
import com.vaadin.data.HasValue;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.data.Binder;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * Created by admin on 21.07.2017.
 */
public class EventForm extends FormLayout {

    private TextField eventName = new TextField("Название");
    private TextField description = new TextField("Описание");
    private ComboBox<Country> countryComboBox = new ComboBox<>("Страна");
    private ComboBox<City> cityComboBox = new ComboBox<>("Город");

    private DateField eventDate = new DateField("Дата");
    private Button save = new Button("Сохранить");
    private Button delete = new Button("Удалить");

    private EventService service = EventService.getInstance();
    private CityCountryService cityCountryService = CityCountryService.getInstance();

    private com.sgu.entity.Event event;
    private City eventCity;
    private User user;
    private EventUI eventUI;
    private Binder<com.sgu.entity.Event> binder = new Binder<>(com.sgu.entity.Event.class);

    public EventForm(EventUI eventUI){
        this.eventUI=eventUI;

        countryComboBox.setItems(cityCountryService.getAllCountyes());
        countryComboBox.addValueChangeListener(new HasValue.ValueChangeListener() {

            public void valueChange(final HasValue.ValueChangeEvent event) {
                if ( countryComboBox.getValue().equals(null)){
                    cityComboBox.setVisible(true);

                }
                else {
                    cityComboBox.setItems(cityCountryService.getListOffAllCityesInCountry(countryComboBox.getValue()));
                    cityComboBox.setVisible(true);

                }
            }
        });
        cityComboBox.setVisible(true);


        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(eventName,description,countryComboBox,cityComboBox,eventDate, buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);
        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());

    }
    public void setEvent(com.sgu.entity.Event event) {
        this.event = event;
        binder.setBean(event);

        eventCity = event.getEventCity();
        delete.setVisible(true);
        setVisible(true);
        eventName.selectAll();
        try {
            cityComboBox.setSelectedItem(eventCity);
            countryComboBox.setSelectedItem(eventCity.getCountryCity());
        } catch (NullPointerException e) {
        }
    }
    private void delete() {

        service.deleteEvent(event);
        eventUI.updateLists();

        setVisible(false);
    }

    private void save() {
        if  (VaadinSession.getCurrent().getAttribute("user") == null)
            Notification.show("Для того чтобы добавить событие нужно войти!");
        else if (cityComboBox.getValue()==null){
            Notification.show("Не выбран город");
        }
        else if(eventName.getValue()==null){
            Notification.show("Введите название события");
        }
        else{
            event.setUser((User) VaadinSession.getCurrent().getAttribute("user"));
            System.out.println(VaadinSession.getCurrent().getAttribute("user").toString());
            event.setEventCity(cityComboBox.getValue());
            service.addEvent(event);
            eventUI.updateLists();
            setVisible(false);
        }
    }

}
