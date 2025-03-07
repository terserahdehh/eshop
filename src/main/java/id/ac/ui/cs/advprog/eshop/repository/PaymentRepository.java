package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentList = new ArrayList<>();
    public Payment save(Payment payment){
        if (payment.getStatus() == PaymentStatus.CHECKING_PAYMENT) {
            if (PaymentMethod.valueOf(payment.getMethod()) == PaymentMethod.VOUCHER){
                if (payment.getPaymentData().get("VoucherCode").matches("^ESHOP(?:\\d+[A-Za-z]+|[A-Za-z]+\\d+)[A-Za-z0-9]*$")  && payment.getPaymentData().get("VoucherCode").length() == 16){
                    payment.setStatus(PaymentStatus.SUCCESS);
                } else {
                    payment.setStatus(PaymentStatus.REJECTED);
                }
            } else if (PaymentMethod.valueOf(payment.getMethod()) == PaymentMethod.COD) {
                if(payment.getPaymentData().containsValue(null) || payment.getPaymentData().containsValue("")){
                    payment.setStatus(PaymentStatus.REJECTED);
                }else{
                    payment.setStatus(PaymentStatus.SUCCESS);
                }
            }
        }
        int i = 0;
        for (Payment savedPayment : paymentList){
            if(savedPayment.getId().equals(payment.getId())){
                paymentList.remove(i);
                paymentList.add(i,payment);
                return payment;
            }
            i +=1;
        }
        paymentList.add(payment);
        return payment;
    }
    public Payment getPayment(String id){
        for (Payment savedPayment : paymentList) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        return null;
    }
    public List<Payment> getAllPayments(){
        List <Payment> result = new ArrayList<>(paymentList);
        return result ;
    }
}