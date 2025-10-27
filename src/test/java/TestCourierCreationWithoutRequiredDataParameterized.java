import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static data.Constants.*;
import static org.apache.http.HttpStatus.*;

@RunWith(Parameterized.class)
public class TestCourierCreationWithoutRequiredDataParameterized extends BaseTest {

    private final String login;
    private final String password;
    private final String firstname;

    public TestCourierCreationWithoutRequiredDataParameterized (String login, String password, String firstname) {

        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }

    @Parameterized.Parameters(name = "{0} {1} {2}")
    public static Object[][] testData() {
        return new Object[][]{
                {null, PASSWORD, FIRSTNAME},
                {LOGIN, null, FIRSTNAME}
        };
    }

    @Test
    @DisplayName("Невозможно создать курьера без логина или пароля")
    @Description("Параметризованный тест на получение ошибки при попытке создания курьера без логина или пароля")
    public void testCannotCreateCourierWithoutLoginOrPassword() {
        CourierModel courier = new CourierModel(login, password, firstname);
        courierApi.createCourier(courier)
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", CoreMatchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

}
