package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        Payment savedPayment = paymentRepository.save(payment);

        assertNotNull(savedPayment);
        assertEquals("1", savedPayment.getId());
        assertEquals(PaymentMethod.VOUCHER, savedPayment.getMethod());
        assertEquals("ESHOP1234IZY5678", savedPayment.getPaymentData().get("VoucherCode"));
    }

    @Test
    void testFindById() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment = new Payment("1", PaymentMethod.VOUCHER, paymentData);
        paymentRepository.save(payment);

        Payment foundPayment = paymentRepository.findById("1");
        assertNotNull(foundPayment);
        assertEquals("1", foundPayment.getId());
    }

    @Test
    void testFindAll() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("VoucherCode", "ESHOP1234IZY5678");
        Payment payment1 = new Payment("1", PaymentMethod.VOUCHER, paymentData1);
        paymentRepository.save(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("VoucherCode", "ESHOP8765XYZ4321");
        Payment payment2 = new Payment("2", PaymentMethod.VOUCHER, paymentData2);
        paymentRepository.save(payment2);

        assertEquals(2, paymentRepository.findAll().size());
    }
}