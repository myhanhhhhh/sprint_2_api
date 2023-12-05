package com.example.mh_cosmetic.dto;

public interface ICartDto {
    Integer getIdUser();
    Integer getIdProduct();
    String getName();
    Double getPrice();

    String getImage();
    Integer getQuantity();
    Integer getMaxQuantity();
    String getBrand();
}
