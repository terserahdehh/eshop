package id.ac.ui.cs.advprog.eshop.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentStatusTest {

    @Test
    public void testContainsValidStatuses() {
        // The contains method checks enum names: "CHECKING_PAYMENT", "REJECTED", "SUCCESS"
        assertTrue(PaymentStatus.contains("CHECKING_PAYMENT"), "Should contain CHECKING_PAYMENT");
        assertTrue(PaymentStatus.contains("REJECTED"), "Should contain REJECTED");
        assertTrue(PaymentStatus.contains("SUCCESS"), "Should contain SUCCESS");
    }

    @Test
    public void testContainsInvalidStatus() {
        // Even though PaymentStatus.CHECKING_PAYMENT.getValue() returns "CHECK_PAYMENT",
        // the contains method expects the enum name ("CHECKING_PAYMENT")
        assertFalse(PaymentStatus.contains("CHECK_PAYMENT"), "Should not contain CHECK_PAYMENT as enum name");
        assertFalse(PaymentStatus.contains("FAILED"), "Should not contain FAILED");
    }

    @Test
    public void testGetValue() {
        // Verify that the getter returns the expected string values.
        assertEquals("CHECK_PAYMENT", PaymentStatus.CHECKING_PAYMENT.getValue(), "CHECKING_PAYMENT should have value CHECK_PAYMENT");
        assertEquals("REJECTED", PaymentStatus.REJECTED.getValue());
        assertEquals("SUCCESS", PaymentStatus.SUCCESS.getValue());
    }
}
