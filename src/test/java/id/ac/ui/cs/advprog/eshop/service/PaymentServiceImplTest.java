package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    Map<String,String> paymentData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP1234ABC5678");
        payments = new ArrayList<>();
        Payment payment1 = new Payment("1","VOUCHER",paymentData);
        payments.add(payment1);
        Payment payment2 = new Payment("2","VOUCHER",paymentData);
        payments.add(payment2);
    }

    @Test
    void testAddPaymentAccepted() {
        Order order = mock(Order.class);
        Payment payment = payments.get(1);

        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(order,"VOUCHER",paymentData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.ACCEPTED.getValue(), result.getStatus());
    }

    @Test
    void testAddPaymentRejected() {
        Order order = mock(Order.class);
        Map<String,String> invalidData = new HashMap<>();

        invalidData.put("voucherCode","ABC");
        Payment payment = new Payment("3","VOUCHER",invalidData);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(order,"VOUCHER",invalidData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }

    @Test
    void testSetStatus() {
        Payment payment = payments.get(1);
        Payment result = paymentService.setStatus(payment, PaymentStatus.ACCEPTED.getValue());

        assertEquals(PaymentStatus.ACCEPTED.getValue(), result.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = payments.get(1);

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment,"MEOW"));
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("abc");
        Payment result = paymentService.getPayment("abc");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();
        List<Payment> results = paymentService.getAllPayments();

        assertEquals(2, results.size());
    }
}