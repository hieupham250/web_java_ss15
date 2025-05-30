package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
    private int id;
    private int idProduct;
    private int idUser;
    private int rating;
    private String comment;
}
