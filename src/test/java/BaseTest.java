import api.CourierApi;
import api.OrderApi;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static data.Constants.BASE_URL;

public class BaseTest {

    protected static CourierApi courierApi = new CourierApi();
    protected static OrderApi orderApi = new OrderApi();

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
}
