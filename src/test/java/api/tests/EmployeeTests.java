package api.tests;

import api.endpoints.EmployeeEndpoints;
import api.payloads.Employee;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class EmployeeTests {
    Employee employeePayload;
    Faker faker;

    @BeforeClass
    public void setupData(){
        faker=new Faker();
        employeePayload=new Employee();

        employeePayload.setName(faker.name().firstName());
        employeePayload.setSalary("10000");
        employeePayload.setAge("37");

    }

    @Test(priority = 1)
    public void createEmployeeTest(){

        Response response= EmployeeEndpoints.createEmployee(employeePayload);
        response.then().log().all();

        // Validate specific parameters in the JSON response body
        response.then()

                .body("data.name", equalTo(employeePayload.getName()))
                .body("data.salary", equalTo(employeePayload.getSalary()))
                .body("data.age", equalTo(employeePayload.getAge()));

        // Additional assertions if needed
        Assert.assertEquals(response.path("status"), "success");
    }

    /*@Test(priority = 3)
    public void getAllEmployeesList(){
        Response response=EmployeeEndpoints.getAllEmployeesList();
        response.then().log().all();
    }
*/
    @Test(priority = 2)
    public void getSingleEmployee(String id){
        String empid="1234";

        Response response1=EmployeeEndpoints.getEmployee(empid);
        response1.then().log().all();


        Response response=EmployeeEndpoints.getEmployee(id);
        response.then().log().all();

        // Validate the status code
        response.then().statusCode(200);

        // Validate specific parameters in the JSON response body
        response.then()

                .body("employee_name", equalTo(employeePayload.getName()))
                .body("employee_salary", equalTo(employeePayload.getSalary()))
                .body("employee_age", equalTo(employeePayload.getAge()));

        // Additional assertions if needed
        Assert.assertEquals(response.path("status"), "success");
    }


}
