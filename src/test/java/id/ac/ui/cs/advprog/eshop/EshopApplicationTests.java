package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class EshopApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context, "The application context should have been loaded.");
    }

    @Test
    void testMainMethodRuns() {
        EshopApplication.main(new String[]{});
        assertNotNull(context, "The application context should be available after running main.");
    }
}
