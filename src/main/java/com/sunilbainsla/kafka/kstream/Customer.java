package com.sunilbainsla.kafka.kstream;

import lombok.Data;

@Data
public class Customer {
    long id;
    String firstName;
    String lastName;
    String email;
    String address;
    String level;
}
