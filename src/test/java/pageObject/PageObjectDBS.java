package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageObjectDBS {


    public WebDriver driver;


    public PageObjectDBS(WebDriver ldriver) {
        driver = ldriver;

        PageFactory.initElements(driver, this);

    }
//**********Locators***********


    @FindBy(linkText = "Learn More")
    WebElement learnMore;

    @FindBy(xpath = "//a[contains(@href,'sustainability')]/parent::li")
    WebElement sustainabilityLink;

    @FindBy(linkText = "Singapore")
    WebElement singaporeLink;

    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> foodSupplyTable;

    @FindBy(linkText = "About")
    WebElement aboutlink;

    @FindBy(linkText = "Who We Are")
    WebElement whoWeArelink;

    @FindBy(linkText = "Our Awards & Accolades")
    WebElement ourAwardslink;

    @FindBy(linkText = "A World First")
    WebElement awardsPage;

    @FindBy(xpath = "//div[@class='row mBot-20']/div[2]/h3")
    List<WebElement> awardslist;

    @FindBy(xpath = "//div[@class='row mBot-20']/div[2]/p")
    List<WebElement> awardDescription;


//*********Actions/Methods**********

    public void clickLearMore() {
        learnMore.click();

    }

    public String verifySustainabilityLink()
    {
       String text=sustainabilityLink.getAttribute("class");
       return text;


    }

    public void clickSingaporeLink() throws InterruptedException {

        Dimension d = driver.manage().window().getSize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,700)");

        Thread.sleep(2000);
        singaporeLink.click();

    }


    public String[][] exportFoodSupplyTable() {

        int i = 0;


        int listsize = foodSupplyTable.size();
        //System.out.println(listsize);

        List<WebElement> dataelemets =driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
        String[][] s = new String[listsize][dataelemets.size()];


        for(i=0;i<listsize;i++)
        {
            //System.out.println("Enter");
            List<WebElement> coleles =driver.findElements(By.xpath("//table/tbody/tr["+(i+1)+"]/td"));
            int count = 0;
            for(WebElement element:coleles)
            {
                String text=element.getText();
                s[i][count] = text;
                count++;
            }

        }


        return s;

    }

    public void clickAboutLink() {

        aboutlink.click();
    }

    public void clickWhoWeAre() throws InterruptedException {

        whoWeArelink.click();

        Dimension d = driver.manage().window().getSize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,550)");
        Thread.sleep(2000);
    }

    public void clickAwardsLink() {
        ourAwardslink.click();
    }

    public String verifyaWardsPage() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,550)");

        String text=awardsPage.getText();

        return text;
    }

    public String[] getAwardslist() {

        int count = 0;
        List<WebElement> awards = awardslist;
        int size = awards.size();
        String[] s = new String[size];


        for (WebElement award : awards) {
            String awardtext = award.getText();
            s[count] = awardtext;
            count++;

        }

        return s;

    }

    public String[] getAwardDescription() {
        int count = 0;
        List<WebElement> awardsdescriptions = awardDescription;
        int size = awardsdescriptions.size();

        String[] s = new String[size];

        for (WebElement awardsdescription : awardsdescriptions) {
            String awarddesctext = awardsdescription.getText();
            s[count] = awarddesctext;
            count++;

        }

        return s;

    }


}
