package com.sgu.ui;


import com.sgu.ui.authentication.Authentication;
import com.sgu.ui.pages.LoginPage;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

/**
 * Created by admin on 05.07.2017.
 */
@SpringUI(path = "login")
@Theme("valo")
@Title("Авторизация")
@PreserveOnRefresh
public class TestUiVaadin extends UI {
    Navigator navigator;
    public static Authentication AUTH;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        AUTH = new Authentication();
        navigator = new Navigator(this, this);

        navigator.addView(LoginPage.NAME, LoginPage.class);






    }


}
