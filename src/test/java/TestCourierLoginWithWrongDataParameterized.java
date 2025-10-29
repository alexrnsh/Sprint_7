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
public class TestCourierLoginWithWrongDataParameterized extends BaseTest {

    private final String login;
    private final String password;

    private static Integer courierIdForTest;

    public TestCourierLoginWithWrongDataParameterized (String login, String password) {
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

                {"wrong" + LOGIN, PASSWORD},
                {LOGIN, "wrong" + PASSWORD},
                {"XYZ" +System.currentTimeMillis(),"XYZ" + System.currentTimeMillis()}
        };
    }

    @Test
    @DisplayName("Невозможно залогинить курьера с неправильными логином и/или паролем")
    @Description("Параметризованный тест с вводом неверных логина и/или пароля, ожидается ошибка логина 404")
    public void testLoginNonExistentCourierReturns404() {

        CourierModel loginRequest = new CourierModel(login, password);
        courierApi.courierLogin(loginRequest)
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", CoreMatchers.equalTo("Учетная запись не найдена"));

    }

    @AfterClass
    public static void tearDown(){
        if (courierIdForTest != null) {
            courierApi.courierDelete(courierIdForTest)
                    .statusCode(SC_OK);
        }
    }

}

