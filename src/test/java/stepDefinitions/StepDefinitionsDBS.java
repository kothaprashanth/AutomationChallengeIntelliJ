package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import cucumber.api.java.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.PageObjectDBS;
import org.apache.log4j.PropertyConfigurator;
import utilities.Helper;

import java.io.FileOutputStream;


public class StepDefinitionsDBS {


    public WebDriver driver;
    PageObjectDBS p;
    public static Logger log;
    public Scenario scenario;

    @Before
    public void start(Scenario scenario) {

        this.scenario = scenario;


        PropertyConfigurator.configure("log4j.properties");
        log = Logger.getLogger("StepDefinitionsDBS.class");
        log.info("Setting WebDriver path and Invoking Driver");
        System.setProperty("webdriver.chrome.driver", "D://WorkspaceBDD//Driver//chromedriver.exe");


    }

    @Given("^User Launch Chrome Browser$")
    public void user_Launch_Chrome_Browser() throws Throwable {

        driver = new ChromeDriver();
        log.info("WebDriver Invoked");


    }

    @When("^user Opens URL \"([^\"]*)\"$")
    public void user_Opens_URL(String url) throws Throwable {


        driver.get(url);
        log.info("Navigated to the application");
        driver.manage().window().maximize();

    }

    @And("^Clicks on LearnMore Button$")
    public void clicks_on_LearnMore_Button() throws Throwable {
        p = new PageObjectDBS(driver);
        p.clickLearMore();


    }

    @And("^User verifies Sustainability page and clicks on Singapore$")
    public void user_verifies_Sustainability_page_and_clicks_on_Singapore() throws Throwable {

        String text = p.verifySustainabilityLink();
        Assert.assertEquals("Validating Sustainability Page", "panel active", text);
        log.debug("Sustainability page is validated");
        p.clickSingaporeLink();


    }

    @Then("^user verifies Table is displayed and export it to Excel$")
    public void user_verifies_Table_is_displayed_and_export_it_to_Excel() throws Throwable {

        String[][] tabData = p.exportFoodSupplyTable();

        //Exporting to Excel
        try
        {
            log.info("Data exporting to excel");
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet sheet = workbook.createSheet("Foodsupplydata");


            for (int i = 0; i < tabData.length; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < tabData[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(tabData[i][j]);

                }
            }


            FileOutputStream fo = new FileOutputStream("FoodInfo.xlsx");
            workbook.write(fo);
            log.debug("Food data is exported to Excel");
        }

        catch (Exception e)
        {
           log.info(e.getMessage());
           log.error("data export is not Successfull");
           Assert.assertTrue(false);

        }


    }

    @When("^user clicks on About Button$")
    public void user_clicks_on_About_Button() throws Throwable {
        log.info("Clicking on About link");
        p.clickAboutLink();

    }

    @And("^clicks on Who we are link$")
    public void clicks_on_Who_we_are_link() throws Throwable {

        log.info("Clicking on Who We Are link");
        p.clickWhoWeAre();

    }

    @And("^clicks on Our  Awards and Accolades$")
    public void clicks_on_Our_Awards_and_Accolades() throws Throwable {
        log.info("Clicking on Awards link");

        p.clickAwardsLink();

    }

    @Then("^user should  navigate to Awards page and validate with \"([^\"]*)\"$")
    public void user_should_navigate_to_Awards_page_and_validate_with(String str) throws Throwable {
        String text = p.verifyaWardsPage();
        if(str.equals(text))
        {
            log.debug("Awards Page is verified");

        }

        else
        {
            log.error("Awards Page is not Displayed");
            Assert.assertTrue(false);

        }

    }

    @Then("^User should validate no of awards as \"([^\"]*)\" print the awards and descriptions in the report$")
    public void user_should_validate_no_of_awards_as_print_the_awards_and_descriptions_in_the_report(String awardscount) throws Throwable {


        String[] awards = p.getAwardslist();

        int awardssize = awards.length;
        String noOfAwards= Integer.toString(awardssize);



        if(noOfAwards.equals(awardscount))

        {
            log.info("No of awards are verified as "+awardscount);
            scenario.write("No of Awards are matches with the given value");

        }
        else
        {

            log.error("No of Awards are not matching with the given value");
            scenario.write("No of Awards are not matching with the given value");
            Assert.assertTrue(false);
        }



        String[] awardsDescription = p.getAwardDescription();

        StringBuilder sb = new StringBuilder("<table border='1'>" + "<tr><th>AWARD NAME</th><th>CAPTION OF AWARD</th></tr>");

        for(int i=0;i<awardssize;i++)
        {
            sb.append("<tr><td>" + awards[i]+"</td><td>"+awardsDescription[i] + "</td></tr>");
        }

        sb.append("</table>");
        scenario.embed(sb.toString().getBytes(), "text/html");

    }

    @After
    public void close(Scenario scenario) throws Exception {
        Helper h= new Helper(driver);
        h.tearDown(scenario);
        driver.quit();

    }


}
