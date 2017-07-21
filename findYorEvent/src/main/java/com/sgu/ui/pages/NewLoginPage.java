package com.sgu.ui.pages;

import com.sgu.entity.City;
import com.sgu.entity.Country;
import com.sgu.entity.User;
import com.sgu.services.CityCountryService;
import com.sgu.services.UserService;
import com.sgu.ui.TestUiVaadin;
import com.vaadin.data.HasValue;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * Created by admin on 21.07.2017.
 */
public class NewLoginPage extends VerticalLayout implements View {

    public static final String NAME = "Registration";
    private CityCountryService service = CityCountryService.getInstance();
    private UserService userService = UserService.getInstance();

    public NewLoginPage(){
        Panel panel = new Panel("Регистрация");
        panel.setSizeUndefined();
        addComponent(panel);
        FormLayout content = new FormLayout();
        TextField login = new TextField("Логин");
        content.addComponent(login);
        PasswordField password = new PasswordField("Пароль");
        content.addComponent(password);
        TextField userName = new TextField("Имя");
        content.addComponent(userName);
        TextField userFam = new TextField("Фамилия");
        content.addComponent(userFam);

        ComboBox<Country> countryes = new ComboBox<>("Название страны");
        ComboBox<City> cityes = new ComboBox<>("Название города");
        countryes.setItems(service.getAllCountyes());
        countryes.setEmptySelectionAllowed(false);
        content.addComponent(countryes);
        countryes.addValueChangeListener(new HasValue.ValueChangeListener() {

            public void valueChange(final HasValue.ValueChangeEvent event) {
                if ( countryes.getValue().equals(null)){
                    cityes.setVisible(false);

                }
                else {
                    cityes.setItems(service.getListOffAllCityesInCountry(countryes.getValue()));
                    cityes.setVisible(true);

                }
            }
        });


        cityes.setEmptySelectionAllowed(false);
        cityes.setVisible(false);
        content.addComponent(cityes);

        DateField birthday = new DateField("День рождения");
        content.addComponent(birthday);
        Button register =new Button("Зарегистрироваться");
        register.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                if ((login.isEmpty()) || (password.isEmpty()) || (userFam.isEmpty()) || (userName.isEmpty())
                    || (cityes.getValue()== null) || birthday.isEmpty())
                    Notification.show("Не заполнены все поля!", Notification.Type.ERROR_MESSAGE);
                else if (userService.checkAvalibleLogin(login.getValue())){
                    Notification.show("Пользователь с таким именем уже зарегистрирован, измените логин",Notification.Type.ERROR_MESSAGE);
                    login.selectAll();
                }

                else{
                    User user = new User();
                    user.setLogin(login.getValue());
                    user.setPassword(password.getValue());
                    user.setBirthday(java.sql.Date.valueOf(birthday.getValue()));
                    user.setName(userName.getValue());
                    user.setSurname(userFam.getValue());
                    user.setUserCity(cityes.getValue());
                    userService.addUser(user);
                    getUI().getNavigator().navigateTo(LoginPage.NAME);
                    Notification.show("Выполните вход");
                }

            }
        });
        register.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        content.addComponent(register);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);



    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
