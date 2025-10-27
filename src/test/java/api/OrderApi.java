package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderApi {
    private static final String ORDER_BASE_V1_URL = "/api/v1/orders";
    private static final String CANCEL_PATH = "/cancel";

    @Step("Создание заказа")
    public ValidatableResponse createOrder(OrderModel order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDER_BASE_V1_URL)
                .then()
                .log().all();
    }

    @Step ("Запрос на получение списка заказов")
    public ValidatableResponse getOrdersList() {
        return given()
                .log().all()
                .get(ORDER_BASE_V1_URL)
                .then()
                .log().all();
    }

    @Step ("Отмена созданного в тесте заказа")
    public ValidatableResponse cancelOrder (Integer trackId) {
       return given()
                .header("Content-type", "application/json")
                .when()
                .put( ORDER_BASE_V1_URL + CANCEL_PATH + "/" + trackId)
                .then()
                .log().all();
    }
}
