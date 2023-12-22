package com.example.cosmetic.service.customer;

import com.example.cosmetic.dto.IOrderHistory;
import com.example.cosmetic.model.customer.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findCustomer(Integer idSer);

    List<IOrderHistory> findHistory(Integer idUser);
}
