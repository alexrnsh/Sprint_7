import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderModel;
import org.hamcrest.CoreMatchers;
import org.junit.After;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class BaseOrderTest extends BaseTest{

    private static final String ORDER_BASE_V1_URL = "/api/v1/orders";
    private static final String CANCEL_PATH = "/cancel";


    protected Integer trackId;

    @After
    public void tearDown(){
        if (trackId != null){
            given()
                    .header("Content-type", "application/json")
                    .when()
                    .put( ORDER_BASE_V1_URL + CANCEL_PATH + "/" + trackId)
                    .then()
                    .statusCode(200);
        }
    }

    @Step("Создание заказа")
    public void createOrderSuccess(OrderModel order){
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDER_BASE_V1_URL)
                .then()
                .log().all()
                .statusCode(201)
                .body("track", CoreMatchers.notNullValue());
        trackId = response.extract().path("id");
    }

    @Step ("Запрос на получение списка заказов")
    public void getOrdersList(){
        given()
                .log().all()
                .get(ORDER_BASE_V1_URL)
                .then()
                .log().all()
                .statusCode(200)
                .body("orders", is(not(empty())));
    }
}
