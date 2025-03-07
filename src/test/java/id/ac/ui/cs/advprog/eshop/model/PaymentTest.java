package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class PaymentTest {
    @Test
    public void testVoucherPaymentDefault() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        assertTrue(payment.getPaymentData().containsKey("VoucherCode"));
        assertEquals("1", payment.getId());
        assertEquals(PaymentMethod.VOUCHER, payment.getMethod());
        assertEquals("ESHOP1234IZY5678", payment.getPaymentData().get("VoucherCode"));
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    public void testCODPaymentDefault() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "100");
        paymentData.put("address", "Denpasar");
        Payment payment = new Payment("2", PaymentMethod.CASH, paymentData);

        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertEquals(PaymentStatus.CHECKING_PAYMENT, payment.getStatus());
        assertEquals("2", payment.getId());
        assertEquals(PaymentMethod.CASH, payment.getMethod());
        assertEquals("Denpasar", payment.getPaymentData().get("address"));
        assertEquals("100", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    public void testCreatePaymentSuccessStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    public void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("VoucherCode", "ESHOP1234IZY5678");
            Payment payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.valueOf("INVALID_STATUS"), paymentData);
        });
    }

    @Test
    public void testPaymentInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(PaymentStatus.valueOf("INVALID_STATUS"));
        });
    }

    @Test
    public void testPaymentRejectStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        payment.setStatus(PaymentStatus.REJECTED);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    public void testValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    public void testInvalidVoucherCodeLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC567");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    public void testInvalidVoucherCodePrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "SHOP1234ABC5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    public void testInvalidVoucherCodeNumericalCharacters() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234ABC567X");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }
}