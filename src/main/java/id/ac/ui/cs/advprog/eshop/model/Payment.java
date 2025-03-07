package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        setMethod(method);
        this.status = "CHECKING_PAYMENT";
        this.paymentData = paymentData;

        switch (this.method) {
            case "CASH":
                if (!isCashDataValid(paymentData)) {
                    throw new IllegalArgumentException("Missing or empty address/deliveryFee for CASH payment");
                }
                break;
            case "VOUCHER":
                String voucher = paymentData.get("VoucherCode");
                if (voucher == null || voucher.length() != 16
                        || !voucher.startsWith("ESHOP")
                        || countDigits(voucher) != 8) {
                    this.status = "REJECTED";
                } else {
                    this.status = "SUCCESS";
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this(id, method, paymentData);
        setStatus(status);
    }

    public void setStatus(String status) {
        if ("SUCCESS".equals(status) || "REJECTED".equals(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
    }

    public void setMethod(String method) {
        if ("VOUCHER".equals(method) || "CASH".equals(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
    }

    private boolean isCashDataValid(Map<String, String> data) {
        String address = data.get("address");
        String fee = data.get("deliveryFee");
        return address != null && !address.trim().isEmpty() &&
                fee != null && !fee.trim().isEmpty();
    }

    private int countDigits(String text) {
        int count = 0;
        for (char ch : text.toCharArray()) {
            if (Character.isDigit(ch)) {
                count++;
            }
        }
        return count;
    }
}



