package model;

public class CourierModel {
    private String login;
    private String password;
    private String firstname;

    public CourierModel(String login, String password, String firstname) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }
    public CourierModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public String toString() {
        return "CourierModel{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }


}
