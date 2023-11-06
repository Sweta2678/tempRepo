package com.plmviewer.model;

public class UserLoginVo {

    private String firstName;
    private String password;
    private String message;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[{firstName=" + firstName + ", password=" + password
                + ", message=" + message + "}]";

    }
}
