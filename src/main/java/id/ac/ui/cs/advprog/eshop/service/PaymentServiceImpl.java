package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String,String> paymentData){
        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                method,
                paymentData
        );

        if(validatePayment(method,paymentData)){
            payment.setStatus(PaymentStatus.ACCEPTED.getValue());
        } else {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        }

        return paymentRepository.save(payment);
    }

    private boolean validatePayment(String method, Map<String,String> data){
        if(method.equals("VOUCHER")){
            String code = data.get("voucherCode");
            return code != null
                    && code.length() == 16
                    && code.startsWith("ESHOP")
                    && code.replaceAll("[^0-9]","").length() == 8;
        }
        if(method.equals("BANK_TRANSFER")){
            String bank = data.get("bankName");
            String ref = data.get("referenceCode");
            return bank != null && !bank.isEmpty()
                    && ref != null && !ref.isEmpty();
        }

        return false;
    }

    @Override
    public Payment setStatus(Payment payment, String status){
        payment.setStatus(status);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
}