package methods;


import org.hamcrest.core.Is;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;


public class ReqresIn {


    @Test(priority = 1)
    public void getuserList() throws InterruptedException {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .log().body();
        Thread.sleep(2000);

    }

    @Test(priority = 2)
    public void testSuccesfullRegistration() throws InterruptedException {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", "eve.holt@reqres.in");
        userInfo.put("password", "pistol");
        given()
                .contentType("application/json")
                .body(userInfo)
                .when()
                .post("https://reqres.in/api/register")

                .then().statusCode(200)
                .body("id", Is.is(4))
                .body("token", Is.is("QpwL5tke4Pnpja7X4"))
                .log().body();

        Thread.sleep(2000);

    }

    @Test(priority = 3)
    public void testUnSuccesfullRegistration() throws InterruptedException {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", "sydney@fife");
        given()
                .contentType("application/json")
                .body(userInfo)
                .when()
                .post("https://reqres.in/api/register")

                .then().statusCode(400)
                .body("error", Is.is("Missing password"))
                .log().body();

        Thread.sleep(2000);


    }
}
