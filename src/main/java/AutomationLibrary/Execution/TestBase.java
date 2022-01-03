package AutomationLibrary.Execution;


import AutomationLibrary.BaseAction.TestInfo;
import PageObject.Action.page;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;


import java.lang.reflect.Method;

public class TestBase {




    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        // Set retry to all methods
        for (ITestNGMethod method : context.getAllTestMethods())
            method.setRetryAnalyzerClass(RetryAnalyzer.class);


        page.init();

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext context) {
        System.out.println("TEST CASE : "+ method.getAnnotation(Test.class).description());
        TestInfo annotation = method.getAnnotation(TestInfo.class);
        annotation.testID();
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite(ITestContext testSuite) {

        page.end();
    }





}
