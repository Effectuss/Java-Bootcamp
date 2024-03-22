package edu.school21.models;

import lombok.*;

import java.math.BigDecimal;

@Data
public class Product {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
}
