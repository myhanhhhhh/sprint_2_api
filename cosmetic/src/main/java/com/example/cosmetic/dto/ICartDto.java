package com.example.cosmetic.dto;

public interface ICartDto {

    Long getIdUser();
    Long getIdProduct();
    String getName();
    Double getPrice();

    String getImage();
    Integer getQuantity();
    Integer getMaxQuantity();
    String getBrand();
}
