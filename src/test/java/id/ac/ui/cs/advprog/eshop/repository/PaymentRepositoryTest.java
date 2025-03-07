package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testSaveNonCheckingPaymentDoesNotChangeStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("7", PaymentMethod.VOUCHER.name(), paymentData);
        payment.setStatus(PaymentStatus.SUCCESS); // already processed

        Payment savedPayment = paymentRepository.save(payment);
        assertEquals(PaymentStatus.SUCCESS, savedPayment.getStatus());
    }

    @Test
    void testUpdateExistingCodPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "10");
        Payment payment = new Payment("8", PaymentMethod.COD.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);
        assertEquals(PaymentStatus.SUCCESS, savedPayment.getStatus());

        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("address", "");  // invalid address
        updatedData.put("deliveryFee", "10");
        Payment updatedPayment = new Payment("8", PaymentMethod.COD.name(), updatedData);

        Payment savedUpdatedPayment = paymentRepository.save(updatedPayment);
        assertEquals(PaymentStatus.REJECTED, savedUpdatedPayment.getStatus());

        Payment fetchedPayment = paymentRepository.getPayment("8");
        assertEquals(PaymentStatus.REJECTED, fetchedPayment.getStatus());
    }

    @Test
    void testUpdateExistingVoucherPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("12", PaymentMethod.VOUCHER.name(), paymentData);

        Payment savedPayment = paymentRepository.save(payment);
        assertEquals(PaymentStatus.SUCCESS, savedPayment.getStatus());

        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("VoucherCode", "INVALIDVOUCHER");
        Payment updatedPayment = new Payment("12", PaymentMethod.VOUCHER.name(), updatedData);

        Payment savedUpdatedPayment = paymentRepository.save(updatedPayment);
        assertEquals(PaymentStatus.REJECTED, savedUpdatedPayment.getStatus());
    }

    @Test
    void testGetPaymentNotFound() {
        Payment payment = paymentRepository.getPayment("nonexistent");
        assertNull(payment);
    }

    @Test
    void testGetAllPayments() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment1 = new Payment("9", PaymentMethod.VOUCHER.name(), paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "456 Side St");
        paymentData2.put("deliveryFee", "5");
        Payment payment2 = new Payment("10", PaymentMethod.COD.name(), paymentData2);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(2, allPayments.size());

        allPayments.remove(0);
        assertEquals(2, paymentRepository.getAllPayments().size());
    }


}
