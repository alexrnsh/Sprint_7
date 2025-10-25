import io.restassured.RestAssured;
import org.junit.Before;

import static data.constants.BASE_URL;

public class BaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

}
