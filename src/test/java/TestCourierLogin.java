import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static data.Constants.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.apache.http.HttpStatus.*;

public class TestCourierLogin extends BaseTest {

    private Integer courierIdForTest;

    @Before
    public void testCourierCreation(){
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

        courierApi.createCourier(courier)
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }


    @Test
    @DisplayName("Проверка успешного логина")
    @Description("Создается курьер и отправляется запрос на логин, ожидается успешный логин")
    public void testCourierCanLoginSuccessfully() {

        CourierModel loginRequest = new CourierModel(LOGIN, PASSWORD);

        ValidatableResponse response = courierApi.courierLogin(loginRequest)
                .statusCode(SC_OK)
                .body("id", notNullValue());

        courierIdForTest = response.extract().path("id");

    }

    @After
    public void testCourierDeletion(){
        if (courierIdForTest != null) {
            courierApi.courierDelete(courierIdForTest).statusCode(SC_OK);
        }
    }

}
