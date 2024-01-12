package runner;


import com.cucumber.listener.Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utilites.ConfigurationReader;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports"}
       // monochrome = true
)

public class APITestRunner {
    static ConfigurationReader fileReader=new ConfigurationReader();
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(fileReader.getReportConfigPath()));
    }

}
