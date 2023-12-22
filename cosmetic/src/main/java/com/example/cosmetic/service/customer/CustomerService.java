package com.example.cosmetic.service.customer;

import com.example.cosmetic.dto.IOrderHistory;
import com.example.cosmetic.model.customer.Customer;
import com.example.cosmetic.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer findCustomer(Integer idSer) {
        return customerRepository.findByIdUser(idSer);
    }

    @Override
    public List<IOrderHistory> findHistory(Integer idUser) {
        return customerRepository.findHistory(idUser);
    }
}
