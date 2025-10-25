import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import model.CourierModel;
import org.junit.Test;

import static data.constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestCourierCreation extends BaseCourierTest {

    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Отправляет запрос на создание курьера и проверяет что вернулся код 201")
    public void testCourierCanBeCreated() {

        CourierModel courierModel = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);

        createCourierSuccess(courierModel);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Description("Отправляет 2 запроса на создание одинаковых курьеров и проверяет что возвращается ошибка 409")
    public void testCannotCreateDuplicateCourier() {

        String login = LOGIN + System.currentTimeMillis();
        CourierModel courierModel1 = new CourierModel(login, PASSWORD, FIRSTNAME);
        CourierModel courierModel2 = new CourierModel(login, PASSWORD, FIRSTNAME);

        createCourierSuccess(courierModel1);
        createDuplicateCourier(courierModel2);
    }

    @Step (("Создать дупликат курьера, в ответ ожидается ошибка"))
    public void createDuplicateCourier(CourierModel courierModel) {

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courierModel)
                .when()
                .post("/api/v1/courier")
                .then()
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }

}


