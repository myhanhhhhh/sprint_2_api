package com.example.cosmetic.service.order;

public interface IOrderService {

    void createOrder(Integer idUser) throws Exception;

    void createOrderDetail(Integer idUser) throws Exception;

    void updateTotalMoney(Integer idUser) throws Exception;
}
