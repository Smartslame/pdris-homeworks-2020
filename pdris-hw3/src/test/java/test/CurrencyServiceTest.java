package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(Config.class)
@ComponentScan(basePackages = "test")
public class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;

    @Test
    public void getCurrencies() {
        currencyService.getCurrencies(5);
        currencyService.getCurrencies(10);
    }
}