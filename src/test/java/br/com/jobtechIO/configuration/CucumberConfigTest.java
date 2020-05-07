package br.com.jobtechIO.configuration;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, plugin = { "pretty",
        "html:target/htmlreports" }, glue = "br.com.jobtechIO.steps", tags = "@SignUpFeature", strict = true)
public class CucumberConfigTest {

}
