package id.ac.ui.cs.advprog.eshop.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentMethodTest {

    @Test
    public void testContainsValidMethods() {
        // The contains method checks enum names, which are "VOUCHER" and "COD"
        assertTrue(PaymentMethod.contains("VOUCHER"), "Should contain VOUCHER");
        assertTrue(PaymentMethod.contains("COD"), "Should contain COD");
    }

    @Test
    public void testContainsInvalidMethod() {
        // Should return false for values not defined in the enum, and is case-sensitive
        assertFalse(PaymentMethod.contains("BANK_TRANSFER"), "Should not contain BANK_TRANSFER");
        assertFalse(PaymentMethod.contains("voucher"), "Should be case-sensitive and not contain 'voucher'");
    }

    @Test
    public void testGetValue() {
        // Verify that the getter returns the expected string values.
        assertEquals("VOUCHER", PaymentMethod.VOUCHER.getValue());
        assertEquals("COD", PaymentMethod.COD.getValue());
    }
}
