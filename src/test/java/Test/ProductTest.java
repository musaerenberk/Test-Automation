package Test;

import Function.Function;
import Listener.Listener;
import bsh.BshMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({Listener.class})
public class ProductTest extends Function {

    private BshMethod method;

    @Test
    public void productSearch() throws InterruptedException {

        searchProduct();


    }
}
