package desktop.pages.Dashboard;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.Popup;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.clickJS;
import static com.vn.chotot.keywords.selenium.Action.getAttributeValue;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class POS extends BasePage {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[@class='pointOfSellArea']")
    private WebElement lbl_POS;

    @FindBy(
            xpath =
                    "//*[contains(@class,'globalButtonsActionBlock') and *[@class='checkoutButtonAction']]")
    private WebElement btn_Payment;

    @FindBy(
            xpath =
                    "//*[contains(@class,'globalButtonsActionBlock') and *[@class='addToCartButtonAction']]")
    private WebElement btn_Back;

    @FindBy(xpath = "//*[@class='premiumServiceBlock']//*[contains(@class,'premiumServiceItemText')]")
    private List<WebElement> lst_PremiumServiceItemBlock;

    @FindBy(xpath = "//*[@class='premiumServiceBlock']//*[@class='premiumServiceItemName']")
    private List<WebElement> lst_PremiumServiceItemName;

    @FindBy(xpath = "//*[@class='premiumServiceBlock']//*[@class='premiumServiceItemPriceText']")
    private List<WebElement> lst_PremiumServiceItemPrice;

    @FindBy(xpath = "//*[@class='premiumServiceBlock']/*[contains(@class,'premiumServiceItem')]")
    private List<WebElement> lst_PremiumServiceItem;

    public void openPOSPage(String adID) {
        log.info("Verify Dashboard Ad displays: " + adID);
        checkAdOnDashboard(adID);

        String posURL = "https://www.chotot." + DOMAIN + "/dashboard/pos/" + adID;
        log.info("URL of POS: " + posURL);
        openURL(posURL);
        waitForElementVisible(lbl_POS, maxWaitTime * 2);
        new Popup().closePopup(minWaitTime);
    }

    public String selectAllPremiumItemByName() {
        Bump bumpPopup = new Bump();

        waitForAllElementVisible(lst_PremiumServiceItemBlock, maxWaitTime * 2);
        waitForAllElementVisible(lst_PremiumServiceItemName, minWaitTime * 2);
        waitForAllElementVisible(lst_PremiumServiceItemPrice, minWaitTime * 2);

        int numberServices = lst_PremiumServiceItemName.size() - 1;
        for (int i = numberServices; i >= 0; i--) {
            // Bump is disabled in some specified timeline
            if (getAttributeValue(lst_PremiumServiceItem.get(i), "className", maxWaitTime)
                    .toUpperCase()
                    .trim()
                    .contains("DISABLED")) continue;

            String premiumName =
                    getAttributeValue(lst_PremiumServiceItemName.get(i), "innerText", maxWaitTime);

            int indexOfPremiumBlock =
                    getIndexOfElementOnListByAttributeValue(
                            lst_PremiumServiceItemBlock, "innerText", premiumName, false);
            String blockPrice =
                    getAttributeValueOfElementOnListByIndex(
                            lst_PremiumServiceItemPrice, indexOfPremiumBlock, "innerText");
            blockPrice =
                    blockPrice.substring(0, blockPrice.indexOf("đ") + 1); // Cut: "5.000 đ / lần" to "5.000 đ"

            // Click on block
            waitForElementVisible(lst_PremiumServiceItemName.get(i), maxWaitTime * 2);
            clickJS(lst_PremiumServiceItemName.get(i));
            selectAllPremiumOption(premiumName, blockPrice);

            // Close popup
            if (i > 0) {
                bumpPopup.clickBuyMore();
                bumpPopup.waitForPopupDisappear();
                waitForAllElementVisible(lst_PremiumServiceItemBlock, maxWaitTime);
            }
        }

        // Get total price
        String total = btn_Payment.getText();
        total = total.substring(total.indexOf("-") + 1, total.indexOf("Đ") - 1).trim();
        log.info("TOTAL: " + total);

        // Click checkout
        bumpPopup.clickCheckout();
        bumpPopup.waitForPopupDisappear();

        return total;
    }

    public String selectPremiumOption(String premiumName, String option, Boolean doesSelectBundle) {
        Bump bumOption = new Bump();
        premiumName = premiumName.toUpperCase();
        if (premiumName.contains("ĐẨY TIN NGAY"))
            return bumOption.selectNormalBump(option, doesSelectBundle);
        else if (premiumName.contains("GÓI ĐẨY TIN")) return bumOption.selectTimerBump(option);
        else if (premiumName.contains("TIN ƯU TIÊN")) return bumOption.selectStickyAdBump(option);
        else if (premiumName.contains("NHÃN NỔI BẬT")) return bumOption.selectAdFeatureBump(option);
        else if (premiumName.contains("GÓI TIẾT KIỆM")) return bumOption.selectSavedBundleBump(option);
        return null;
    }

    public String selectAllPremiumOption(String premiumName, String defaultPrice) {
        Bump bumOption = new Bump();
        premiumName = premiumName.toUpperCase().trim();
        if (premiumName.contains("ĐẨY TIN NGAY")) return bumOption.selectAllNormalBump(true);
        else if (premiumName.contains("GÓI ĐẨY TIN")) return bumOption.selectAllTimerBump(defaultPrice);
        else if (premiumName.contains("TIN ƯU TIÊN"))
            return bumOption.selectAllStickyAdBump(defaultPrice);
        else if (premiumName.contains("NHÃN NỔI BẬT"))
            return bumOption.selectAllAdFeatureBump(defaultPrice);
        else if (premiumName.contains("GÓI TIẾT KIỆM"))
            return bumOption.selectAllSavedBundleBump(defaultPrice);
        return null;
    }

    public void verifyListDefaultPrice(List<String> listDefaultPrice) {
        // Wait for page load
        waitForElementVisible(lbl_POS, maxWaitTime * 2);
        waitForAllElementVisible(lst_PremiumServiceItemPrice, maxWaitTime);

    /* Do not verify default prices because the price can be changed by other team
    String actualPrice = "", expectPrice = "";
    for (int i = 0; i < lst_PremiumServiceItemPrice.size(); i++) {
        actualPrice = getAttributeValue(lst_PremiumServiceItemPrice.get(i), "innerText", maxWaitTime).split("/")[0].trim();
        expectPrice = listDefaultPrice.get(i).trim();
        verifyEquals(actualPrice, expectPrice, FailureHandling.CONTINUE_ON_FAILURE);
    } */
    }

    public void verifyPosItems() {
        log.info("Verify POS displays");
        waitForElementVisible(lbl_POS, maxWaitTime * 3);
        verifyElementVisible(lbl_POS, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
        verifyAllElementVisible(
                lst_PremiumServiceItem, minWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
        verifyAllElementVisible(
                lst_PremiumServiceItemBlock, minWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
        verifyAllElementVisible(
                lst_PremiumServiceItemName, minWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
        verifyAllElementVisible(
                lst_PremiumServiceItemPrice, minWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyPosItems(FailureHandling failureHandling) {
        log.info("Verify POS displays");
        waitForElementVisible(lbl_POS, maxWaitTime * 3);
        verifyElementVisible(lbl_POS, maxWaitTime, failureHandling);
        verifyAllElementVisible(lst_PremiumServiceItem, minWaitTime, failureHandling);
        verifyAllElementVisible(lst_PremiumServiceItemBlock, minWaitTime, failureHandling);
        verifyAllElementVisible(lst_PremiumServiceItemName, minWaitTime, failureHandling);
        verifyAllElementVisible(lst_PremiumServiceItemPrice, minWaitTime, failureHandling);
    }

    public boolean waitPOSPageDisplayed() {
        return waitForElementVisible(lbl_POS, maxWaitTime * 9);
    }
}
