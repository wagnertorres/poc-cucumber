import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "not @tag1 and not @tag2",
        plugin = {"pretty", "html:target/build/reports/feature.html"},
        features = {"classpath:features"}
)
public class RunTest {

}
