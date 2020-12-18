package Pages;

import Abstract.MainFunction;
import Type.DefaultValues;
import org.testng.Assert;

public class Pages extends MainFunction {

    public void homePage() {
        Assert.assertEquals("My Store", driver.getTitle());
        System.out.println("Website title checked");
        log.info("Login to Site Homepage");
        ScreenShot();
    }


    public void signIn () {
        Sleep();
        classname(DefaultValues.SIGN_IN).click();
        Sleep();
        id(DefaultValues.TXT_USER_NAME).sendKeys(DefaultValues.USERNAME);
        id(DefaultValues.TXT_PASSWORD).sendKeys(DefaultValues.PASSWORD);
        xpath(DefaultValues.GO_ON).click();
        System.out.println("Login process is successful");
        log.info("Login process is successful");
        ScreenShot();

    }

    public void searchTshirt () {

        id(DefaultValues.SEARCH_INPUT).sendKeys("t-shirts");
        name(DefaultValues.SEARCH_BTN).click();
        Sleep();
        log.info("The result is equivalent to the searched");
        System.out.println("The result is equivalent to the searched");
        ScreenShot();

    }

    public void makeList(){
        classname(DefaultValues.LIST).click();
        Sleep();
        log.info("Making List Successful");
        System.out.println("Making List Successful");
        ScreenShot();
    }

    public void makeGrid(){
        classname(DefaultValues.GRID).click();
        Sleep();
        log.info("Making Grid Successful");
        System.out.println("Making Grid Successful");
        ScreenShot();

    }
    public void productReview(){
        xpath(DefaultValues.REVIEW).click();
        Sleep();
        log.info("Product Review, Successful");
        System.out.println("Product Review, Successful");
        ScreenShot();
    }
    public void viewLarger(){
        id(DefaultValues.VIEWLARGER).click();
        Sleep();
        log.info("View Larger,  Successful");
        System.out.println("View Larger, Successful");
        ScreenShot();

    }
    public void closeViewLarger(){
        xpath(DefaultValues.CLSVIEWLARGER).click();
        Sleep();
        log.info(" Close View Larger,  Successful");
        System.out.println(" Close View Larger, Successful");
        ScreenShot();

    }

    public void addToWishlist(){
        id(DefaultValues.WISHLIST).click();
        Sleep();
        log.info(" add to wish list,  Successful");
        System.out.println(" add to wish list, Successful");
        ScreenShot();
        xpath(DefaultValues.CLSWISHLIST).click();
        Sleep();

    }
    public void addToCart(){
        xpath(DefaultValues.ADDTOCART).click();
        Sleep();
        xpath(DefaultValues.ADDTOCARTNXT).click();
        Sleep();
        log.info(" add to cart,  Successful");
        System.out.println(" add to cart , Successful");
        ScreenShot();

    }
    public void unloadToCart(){
        classname(DefaultValues.UNLOADCART).click();
        Sleep();
        log.info(" unload to cart,  Successful");
        System.out.println(" unload to cart , Successful");
        ScreenShot();
    }
}


