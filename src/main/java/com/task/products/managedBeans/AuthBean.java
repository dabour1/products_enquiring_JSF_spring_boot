package com.task.products.managedBeans;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.task.products.dto.AuthenticateUserDto;
import com.task.products.dto.UserRegistrationDto;
import com.task.products.exceptions.UserAlreadyExistsException;
import com.task.products.services.UserService;

import lombok.Data;

@Component(value = "AuthBean")
@ViewScoped
@Data
public class AuthBean implements Serializable {
    @Autowired
    private UserService userService;

    private UserRegistrationDto user = new UserRegistrationDto();
    private AuthenticateUserDto loginsUser = new AuthenticateUserDto();

    public String register() {
        try {
            userService.registerUser(user);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registration successful"));
            return "login?faces-redirect=true";
        } catch (UserAlreadyExistsException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), " "));
            return null;
        }
    }

    public String login() {
        try {
            userService.authenticate(loginsUser.getUsername(), loginsUser.getPassword());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Login successful"));
            return "products.xhtml?faces-redirect=true";

        } catch (BadCredentialsException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed: Invalid credentials",
                            "Login failed: "));
            return null;
        }
    }

}