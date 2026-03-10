package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    Payment payment;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","ESHOP1234ABC5678");
        payment = new Payment("1","VOUCHER",data);
    }

    @Test
    void testSaveCreate(){
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(),result.getId());
        assertEquals(payment.getId(),findResult.getId());
    }

    @Test
    void testFindByIdIfIdFound(){
        paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(),findResult.getId());
    }

    @Test
    void testFindByIdIfNotFound(){
        Payment findResult = paymentRepository.findById("abc");
        assertNull(findResult);
    }

    @Test
    void testFindAll(){
        paymentRepository.save(payment);
        assertEquals(1,paymentRepository.findAll().size());
    }
}