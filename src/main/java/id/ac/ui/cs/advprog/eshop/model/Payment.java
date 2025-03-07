package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    PaymentMethod method;
    PaymentStatus status;
    private Map<String, String> paymentData;

    public Payment(String id, PaymentMethod method, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.status = PaymentStatus.CHECKING_PAYMENT;
        this.paymentData = paymentData;

        if (method == PaymentMethod.CASH) {
            if (!paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee")) {
                throw new IllegalArgumentException("Missing address or deliveryFee for CASH method");
            }
        } else if (method == PaymentMethod.VOUCHER) {
            String voucherCode = paymentData.get("VoucherCode");
            if (voucherCode == null
                    || voucherCode.length() != 16
                    || !voucherCode.startsWith("ESHOP")
                    || voucherCode.replaceAll("[^0-9]", "").length() != 8) {
                this.status = PaymentStatus.REJECTED;
            } else {
                this.status = PaymentStatus.SUCCESS;
            }
        }
    }

    public Payment(String id, PaymentMethod method, PaymentStatus status, Map<String, String> paymentData) {
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(PaymentStatus status) {
        if (status == PaymentStatus.SUCCESS || status == PaymentStatus.REJECTED) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setMethod(PaymentMethod method) {
        if (method == PaymentMethod.VOUCHER || method == PaymentMethod.CASH) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
    }
}