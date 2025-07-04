package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    private int orderId;
    private int idUser;
    private String recipientName;
    private String address;
    private String phoneNumber;
    private LocalDateTime orderDate;
}
