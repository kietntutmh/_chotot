package projects.functions;

import com.jayway.jsonpath.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.driver.selenium.DriverFactory.instance;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

/**
 * This function is used for Keyword-BDD Framework to read element from JSON
 * It's not applied for he current using way
 */
@Deprecated
public class TestBDDFunctions {
    private final static String PAGEOBJ_JSONFILE_PATH = System.getProperty("user.dir") + "/src/test/java/projects/pages/pageObjs_";
    private static File elementJsonFile = null;
    private static String pageObjName = "";
    private static String PROJECT_NAME = "";
    private static HashMap<String, String> pageObjBDD = null;

    public TestBDDFunctions() {
    }

    public static void setPageObjNameBDD(String pageObjName) {
        System.out.println("Current Page: " + pageObjName);
        pageObjName = pageObjName.toLowerCase().trim().replace(" ", "");
        TestBDDFunctions.pageObjName = pageObjName;

        //Set Page_Object to Current_Page_Object
        TestBDDFunctions.pageObjBDD = setPageObjBDD();
    }

    private static HashMap<String, String> setPageObjBDD(){
        initialize();
        try {
            return JsonPath.parse(elementJsonFile).read("$." + pageObjName, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public static HashMap<String, String> getPageObjBDD(){
        return pageObjBDD;
    }

    public static void setProjectName(String projectName) {
        TestBDDFunctions.PROJECT_NAME = projectName.toUpperCase().trim().replace(" ", "");
    }

    @Deprecated
    public static String getProjectName() {
        return PROJECT_NAME;
    }

    //Actions
    private static void initialize() {
        if(PROJECT_NAME.isEmpty())
            throw new SkipException("PROJECT NAME IS NULL");    //PROJECT NAME IS SETUP IN SCENARIOS as a backup step

        if(elementJsonFile == null)
            elementJsonFile = new File(PAGEOBJ_JSONFILE_PATH + PROJECT_NAME + ".json");
    }

    public static WebElement getWebElementBDD(String elementObjName){
        initialize();
        elementObjName = elementObjName.toLowerCase().trim().replace(" ", "");

        String query = String.format("$.%s.%s", pageObjName, elementObjName);
        try {
            By eLocator = By.xpath(JsonPath.parse(elementJsonFile).read(query, String.class));
            waitForElementVisible(eLocator, maxWaitTime * 2);   //OPTIONAL
            return instance().getWebDriver().findElement(eLocator);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get Element in Page Object
    public static List<String> getAllVisibleElementNamesBDD(){
        List<String> allElementNames = new ArrayList<>();
        allElementNames.addAll(getPageObjBDD().keySet());

        Iterator<String> iter = allElementNames.iterator();
        while (iter.hasNext()) {
            String eleName = iter.next();
            if(eleName.contains("url") || eleName.contains("err") || eleName.contains("validation"))
                iter.remove();
        }
        return allElementNames;
    }

    public String getElementObjBDD(String elementName){
        if(pageObjBDD == null)
            TestBDDFunctions.pageObjBDD = setPageObjBDD();
        return pageObjBDD.get(elementName);
    }
}
