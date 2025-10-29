import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.After;
import org.junit.Test;

import static data.Constants.*;
import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.*;

public class TestCourierCreation extends BaseTest {

    private final CourierModel courierModel = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Отправляет запрос на создание курьера и проверяет что вернулся код 201")
    public void testCourierCanBeCreated() {

        courierApi.createCourier(courierModel)
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Description("Отправляет 2 запроса на создание одинаковых курьеров и проверяет что возвращается ошибка 409")
    public void testCannotCreateDuplicateCourier() {

        CourierModel duplicateCourier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

        courierApi.createCourier(courierModel)
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

        courierApi.createCourier(duplicateCourier).statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));

    }

    @After
    public void testCourierDeletion(){

        Integer courierIdForTest  = courierApi.courierLogin(courierModel).statusCode(SC_OK)
                .extract()
                .path("id");
        if (courierIdForTest != null) {
            courierApi.courierDelete(courierIdForTest).statusCode(SC_OK);
        }
    }

}


