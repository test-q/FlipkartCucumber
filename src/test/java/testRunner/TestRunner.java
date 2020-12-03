package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resource/features",
		glue = {"stepDefinations"},
		monochrome = true,
		dryRun = false,
		strict = true,
		plugin = {"pretty", "html:test-output", "json:Json-Report/report.json", "junit:Junit-Report/report.xml"}
		)
public class TestRunner {

}
