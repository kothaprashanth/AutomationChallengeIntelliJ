package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "D://WorkspaceBDD//Features//DBSSample.feature",
        glue="stepDefinitions",
       // dryRun = true,
        monochrome = true,
        plugin= {"pretty","html:test-output"},

        tags = {"@positiveTest,@negativeTest"}


)

public class TestRunnerDBS {
}
