package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSaveVoucherPaymentSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(PaymentStatus.SUCCESS, savedPayment.getStatus());
    }

    @Test
    void testSaveVoucherPaymentRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "INVALIDVOUCHER");
        Payment payment = new Payment("2", PaymentMethod.VOUCHER.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(PaymentStatus.REJECTED, savedPayment.getStatus());
    }

    @Test
    void testSaveCodPaymentSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "10");
        Payment payment = new Payment("3", PaymentMethod.COD.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(PaymentStatus.SUCCESS, savedPayment.getStatus());
    }

    @Test
    void testSaveCodPaymentRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10");
        Payment payment = new Payment("4", PaymentMethod.COD.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(PaymentStatus.REJECTED, savedPayment.getStatus());
    }

    @Test
    void testSaveCodPaymentNullAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "10");
        Payment payment = new Payment("5", PaymentMethod.COD.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(PaymentStatus.REJECTED, savedPayment.getStatus());
    }

    @Test
    void testSaveVoucherPaymentInvalidLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY567");
        Payment payment = new Payment("6", PaymentMethod.VOUCHER.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(PaymentStatus.REJECTED, savedPayment.getStatus());
    }
}