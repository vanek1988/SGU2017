package com.sgu.ui;

import com.sgu.entity.Event;
import com.sgu.entity.User;
import com.sgu.services.EventService;
import com.sgu.ui.forms.EventForm;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * Created by admin on 21.07.2017.
 */
@SpringUI(path = "event")
@Theme("valo")
@Title("События")
public class EventUI extends UI {

    private EventService service = EventService.getInstance();
    private Grid<com.sgu.entity.Event> eventGrid = new Grid<>(com.sgu.entity.Event.class);
    private TextField filterText = new TextField();
    private EventForm eventForm = new EventForm(this);
    private CheckBox checkBox = new CheckBox("События в твоем городе");
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();

        filterText.setPlaceholder("поиск по названию события");
        filterText.addValueChangeListener(e -> updateLists());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Удаление поиска");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());


        checkBox.addValueChangeListener(event ->{
            if  (VaadinSession.getCurrent().getAttribute("user") == null) {
                Notification.show("Для того чтобы добавить событие нужно войти!");
                checkBox.setValue(false);
            }
            else
                updateLists();

                });

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addEventBtn = new Button("Добавить событие");
        addEventBtn.addClickListener(e->{
            eventGrid.asSingleSelect().clear();
            eventForm.setEvent(new com.sgu.entity.Event());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering,addEventBtn,checkBox);

        eventGrid.setColumns("eventName","user","eventCity","description","eventDate");

        HorizontalLayout main = new HorizontalLayout(eventGrid, eventForm);
        main.setSizeFull();
        eventGrid.setSizeFull();
        main.setExpandRatio(eventGrid, 1);

        layout.addComponents(toolbar, main);


        updateLists();
        setContent(layout);

        eventForm.setVisible(false);
        eventGrid.asSingleSelect().addValueChangeListener(event-> {
            if (event.getValue() == null){
                eventForm.setVisible(false);
            }
            else{
                eventForm.setEvent(event.getValue());

            }
        });


    }
    public void updateLists(){
        List<com.sgu.entity.Event> list;
        if (checkBox.getValue() == false){

            if (filterText.isEmpty()){
                list = service.getAllEvents();
            }
            else
                list = service.getAllEvents(filterText.getValue());

        }
        else{
            User user = (User)VaadinSession.getCurrent().getAttribute("user");
            if (filterText.isEmpty()){
                list = service.getAllEventsFromCity( user.getUserCity());
            }
            else
                list = service.getAllEventsFromCity(user.getUserCity(),filterText.getValue());

        }
        eventGrid.setItems(list);
    }
}
