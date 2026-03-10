package id.ac.ui.cs.advprog.eshop.enums;

import java.util.Arrays;

public enum PaymentStatus {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String value;

    PaymentStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static boolean contains(String value){
        return Arrays.stream(values())
                .anyMatch(status -> status.value.equals(value));
    }
}