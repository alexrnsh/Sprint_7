import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static data.Constants.*;
import static org.hamcrest.Matchers.equalTo;
import static org.apache.http.HttpStatus.*;


@RunWith(Parameterized.class)
public class TestCourierLoginWithoutRequiredDataParameterized extends BaseTest {

    private final String login;
    private final String password;

    private static Integer courierIdForTest;

    public TestCourierLoginWithoutRequiredDataParameterized(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @BeforeClass
    public static void createTestCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        courierApi.createCourier(courier)
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

        //сохраняется для очистки после теста
        courierIdForTest = courierApi.courierLogin(courier).statusCode(SC_OK)
                .extract()
                .path("id");

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

        CourierModel loginRequest = new CourierModel(login, password);
        courierApi.courierLogin(loginRequest)
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", CoreMatchers.equalTo("Недостаточно данных для входа"));

    }

    @AfterClass
    public static void tearDown(){
        if (courierIdForTest != null) {
            courierApi.courierDelete(courierIdForTest)
                    .statusCode(SC_OK);
        }
    }

}

