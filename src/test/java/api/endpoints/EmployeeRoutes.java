package api.endpoints;

public class EmployeeRoutes {


    public static String baseURL="https://dummy.restapiexample.com/api/v1";
    //get all employee data
    public static String employees=baseURL+"/employees";
    //get single employee data
    public static String singleEmployee=baseURL+"/employee/{id}";
    //Create new record in database
    public static String createEmployee=baseURL+"/create";
    public static String updateEmployee=baseURL+"/update/{id}";
    public static String deleteEmployee=baseURL+"/delete/{id}";



}
