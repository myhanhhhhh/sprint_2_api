package com.example.cosmetic.dto;

public interface IOrderHistory {
    String getDateOfOrder();

    String getNameProduct();

    Double getPriceOfOrder();
    Integer getQuantityOrder();
    String getTimeOfOrder();
}

