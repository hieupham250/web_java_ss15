package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private int id;
    private int idUser;
    private int idProduct;
    private int quantity;
}
