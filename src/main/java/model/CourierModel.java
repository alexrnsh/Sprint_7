package model;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CourierModel {
    private String login;
    private String password;
    private String firstname;

    public CourierModel(String login, String password) {
        this.login = login;
        this.password = password;
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
