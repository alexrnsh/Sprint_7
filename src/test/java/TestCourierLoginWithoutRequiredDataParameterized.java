import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static data.constants.*;

@RunWith(Parameterized.class)
public class TestCourierLoginWithoutRequiredDataParameterized extends BaseCourierTest {

    private final String login;
    private final String password;

    public TestCourierLoginWithoutRequiredDataParameterized(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] testData() {
        return new Object[][]{
                {null, PASSWORD},
                {LOGIN, ""},
        };
    }

    @Test
    @DisplayName("Невозможно залогинить курьера без логина или пароля")
    @Description("Параметризованный тест с вводом пустых логина и пароля, ожидается ошибка логина 400")
    public void testLoginWithoutRequiredDataReturns400() {

        CourierModel courier = new CourierModel(LOGIN + System.currentTimeMillis(), PASSWORD, FIRSTNAME);
        createCourierSuccess(courier);

        CourierModel loginRequest = new CourierModel(login, password);
        courierLoginError400(loginRequest);

    }


}

