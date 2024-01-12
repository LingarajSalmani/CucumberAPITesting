package api.endpoints;

import api.payloads.Employee;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class EmployeeEndpoints {
//    RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());

    public static Response createEmployee(Employee payload){

        RestAssured.useRelaxedHTTPSValidation();

        Response response =given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(payload).
                when().post(EmployeeRoutes.createEmployee);

        return response;
    }



    public static Response getAllEmployees(){
        // Disable SSL certificate validation using a custom TrustManager (not recommended for production)
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());

        Response response=given().
                get(EmployeeRoutes.employees);

        return response;

    }

    public static Response getEmployee(String empid){
        // Disable SSL certificate validation using a custom TrustManager (not recommended for production)
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());

        Response response=given().

                pathParam("id", empid).
                when().get(EmployeeRoutes.singleEmployee);


        return response;

    }

    public static Response deleteEmployee(String empid){
        // Disable SSL certificate validation using a custom TrustManager (not recommended for production)
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());

        Response response=given().

                pathParam("id", empid).
                when().get(EmployeeRoutes.deleteEmployee);


        return response;

    }

        /*public static Response createEmployee2(String payload){
        // Disable SSL certificate validation using a custom TrustManager (not recommended for production)
        // RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());
// Disable SSL certificate validation (not recommended for production)
        RestAssured.useRelaxedHTTPSValidation();

        Response response =given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(payload).
                when().post(EmployeeRoutes.createEmployee);

        return response;
    }*/
}
