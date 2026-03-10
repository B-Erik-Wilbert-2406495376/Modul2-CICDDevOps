package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    @Test
    void testCreatePayment() {
        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","ESHOP1234ABC5678");

        Payment payment = new Payment("1","VOUCHER",data);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testInvalidVoucher() {
        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","ABC");

        Payment payment = new Payment("1","VOUCHER",data);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferSuccess() {
        Map<String,String> data = new HashMap<>();
        data.put("bankName","BCA");
        data.put("referenceCode","INV123");

        Payment payment = new Payment("1","BANK_TRANSFER",data);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testBankTransferRejected() {
        Map<String,String> data = new HashMap<>();
        data.put("bankName","");
        data.put("referenceCode","");

        Payment payment = new Payment("1","BANK_TRANSFER",data);

        assertEquals("REJECTED", payment.getStatus());
    }
}
