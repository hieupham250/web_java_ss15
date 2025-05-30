package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double currentPrice;
}