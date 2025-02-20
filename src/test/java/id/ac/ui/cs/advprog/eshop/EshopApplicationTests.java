package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // Verifies that the Spring Boot application context loads successfully.
    }

    @Test
    void testMainMethodRuns() {
        // Invokes the main method to ensure the application starts without errors.
        EshopApplication.main(new String[]{});
    }
}
