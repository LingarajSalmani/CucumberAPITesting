package stepdefinitions;

import api.endpoints.EmployeeEndpoints;
import api.payloads.Employee;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class EmployeeStepDefinitions {

    Faker faker;
    Employee employeePayload;

    private Response response;
    private String employeeId;

    public  Employee setupData(){
        faker=new Faker();
        employeePayload=new Employee();

        employeePayload.setName(faker.name().firstName());
        employeePayload.setSalary("10000");
        employeePayload.setAge("37");

        return employeePayload;

    }


    @Given("Create new employee api endpoint")
    public void createNewEmployeeApiEndpoint() {

    }

    @When("I make a POST request to create new employee endpoint")
    public void makePostRequestToCreateNewEmployeeEndpoint() {
        // Assuming you have a request payload for creating a new employee
        faker=new Faker();
        employeePayload=new Employee();

        employeePayload.setName(faker.name().firstName());
        employeePayload.setSalary("10000");
        employeePayload.setAge("37");

        response= EmployeeEndpoints.createEmployee(employeePayload);
        response.then().log().all();

    }

    @Then("the response status code should be 200")
    public void verifyResponseStatusCode() {
        response.then().statusCode(200);
    }

    @Then("the response should contain name salary and age")
    public void verifyResponseContainsNameSalaryAndAge() {

        // Validate specific parameters in the JSON response body
        response.then()

                .body("data.name", equalTo(employeePayload.getName()))
                .body("data.salary", equalTo(employeePayload.getSalary()))
                .body("data.age", equalTo(employeePayload.getAge()));

        // Additional assertions if needed
        Assert.assertEquals(response.path("status"), "success");
    }


    @Then("I make a GET request to get single employee details endpoint")
    public void i_make_a_get_request_to_get_single_employee_details_endpoint() {
       /* ResponseBody body=response.body();
        String rsbody= body.asString();
        JsonPath jpath=new JsonPath(rsbody);*/
        String name=response.jsonPath().getString("data.name");
        int empid=response.jsonPath().getInt("data.id");
        String id= Integer.toString(empid);

        response=EmployeeEndpoints.getEmployee(id);

        response.then().log().all();

    }
    @Then("the response should contains the name salary and age of newly created employee")
    public void the_response_should_contains_the_name_salary_and_age_of_newly_created_employee() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("End of Test");
    }


    @Then("verify that employee delete successfull message is returned")
    public void verify_that_employee_delete_successfull_message_is_returned() {
        response.then().log().all();
        Assert.assertEquals(response.path("status"), "success");
        String status=response.path("status");
        System.out.println("Status :"+status);
        Assert.assertEquals(response.path("message"), "successfully! deleted Records");
        String message=response.path("message");
        System.out.println("Message :"+message);

    }
   /* @Then("I make a GET request to delete the employee")
    public void i_make_a_get_request_to_delete_the_employee() {
        int empid=response.jsonPath().getInt("data.id");
        String id= Integer.toString(empid);

        response=EmployeeEndpoints.deleteEmployee(id);

        response.then().log().all();

    }*/

    @Then("I make a GET request to delete the employee")
    public void i_make_a_get_request_to_delete_the_employee() {
        // Write code here that turns the phrase above into concrete actions
        int empid=response.jsonPath().getInt("data.id");
        String id= Integer.toString(empid);

        response=EmployeeEndpoints.deleteEmployee(id);

        response.then().log().all();
    }


    @Given("GET all employees endpoint")
    public void get_all_employees_endpoint() {

    }
    @When("I make a GET request to all employee endpoint")
    public void i_make_a_get_request_to_all_employee_endpoint() {
        response=EmployeeEndpoints.getAllEmployees();
         }
    @Then("verify the success message of get all employee endpoint")
    public void verify_the_success_message_of_get_all_employee_endpoint() {
        String message=response.path("message");

        Assert.assertEquals(message, "Successfully! All records has been fetched.");
    }





}
