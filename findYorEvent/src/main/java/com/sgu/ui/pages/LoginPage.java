package com.sgu.ui.pages;

import com.sgu.ui.TestUiVaadin;
import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

/**
 * Created by admin on 06.07.2017.
 */

public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";

    public LoginPage(){
        Panel panel = new Panel("Вход");
        panel.setSizeUndefined();
        addComponent(panel);


        FormLayout content = new FormLayout();
        TextField username = new TextField("Логин");
        content.addComponent(username);
        PasswordField password = new PasswordField("Пароль");
        content.addComponent(password);

        Button send = new Button("Войти");
        send.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(TestUiVaadin.AUTH.authenticate(username.getValue(), password.getValue())){
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());


                }else{
                    Notification.show("Неправильный логин/пароль!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
