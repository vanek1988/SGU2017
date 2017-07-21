package com.sgu.ui.authentication;

import com.sgu.entity.User;
import com.sgu.services.UserService;

/**
 * Created by admin on 06.07.2017.
 */
public class Authentication {
    private String username;
    private String password;
    private UserService service = UserService.getInstance();

    public Authentication() {

    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getUsername(){
        return this.username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private String getPassword(){
        return this.password;
    }

    public Boolean authenticate(String username, String password){
        if (service.checkAvalibleLogin(username)){
            User user = service.getUser(username);
            setUsername(user.getLogin());
            setPassword(user.getPassword());

            if(username.equals(getUsername()) && password.equals(getPassword())){
                return true;
            }
        }
        return false;
    }
}
