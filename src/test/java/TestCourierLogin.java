import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;
import static data.constants.*;

public class TestCourierLogin extends BaseCourierTest {

    @Test
    @DisplayName("Проверка успешного логина")
    @Description("Создается курьер и отправляется запрос на логин, ожидается успешный логин")
    public void testCourierCanLoginSuccessfully() {

        String login = LOGIN + System.currentTimeMillis();

        CourierModel courier = new CourierModel(login, PASSWORD, FIRSTNAME);
        createCourierSuccess(courier);

        CourierModel loginRequest = new CourierModel(login, PASSWORD);
        courierLoginSuccess(loginRequest);

    }

}
