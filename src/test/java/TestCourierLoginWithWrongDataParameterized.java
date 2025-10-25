import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static data.constants.*;

@RunWith(Parameterized.class)
public class TestCourierLoginWithWrongDataParameterized extends BaseCourierTest {

    private final String login;
    private final String password;

    public TestCourierLoginWithWrongDataParameterized (String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] testData() {
        return new Object[][]{

                {"wrong" + LOGIN, PASSWORD},
                {LOGIN, "wrong" + PASSWORD},
                {"XYZ" +System.currentTimeMillis(),"XYZ" + System.currentTimeMillis()}
        };
    }

    @Test
    @DisplayName("Невозможно залогинить курьера с неправильными логином и/или паролем")
    @Description("Параметризованный тест с вводом неверных логина и/или пароля, ожидается ошибка логина 404")
    public void testLoginNonExistentCourierReturns404() {

        CourierModel courier = new CourierModel(LOGIN + System.currentTimeMillis(), PASSWORD, FIRSTNAME);
        createCourierSuccess(courier);

        CourierModel loginRequest = new CourierModel(login, password);
        courierLoginError404(loginRequest);

    }

}

