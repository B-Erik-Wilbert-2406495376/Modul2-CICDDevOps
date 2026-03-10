package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    Map<String,String> paymentData;

    @BeforeEach
    void setUp(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentDefaultStatus(){

        Payment payment = new Payment("1","VOUCHER",paymentData);

        assertEquals("1",payment.getId());
        assertEquals("VOUCHER",payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(),payment.getStatus());
        assertEquals(paymentData,payment.getPaymentData());
    }

    @Test
    void testSetStatusAccepted(){

        Payment payment = new Payment("1","VOUCHER",paymentData);

        payment.setStatus(PaymentStatus.ACCEPTED.getValue());

        assertEquals(PaymentStatus.ACCEPTED.getValue(),payment.getStatus());
    }

    @Test
    void testSetStatusRejected(){

        Payment payment = new Payment("1","VOUCHER",paymentData);

        payment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(),payment.getStatus());
    }

    @Test
    void testSetStatusInvalid(){

        Payment payment = new Payment("1","VOUCHER",paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
}