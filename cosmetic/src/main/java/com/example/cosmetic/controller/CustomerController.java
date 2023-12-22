package com.example.cosmetic.controller;

import com.example.cosmetic.dto.IOrderHistory;
import com.example.cosmetic.model.customer.Customer;
import com.example.cosmetic.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/info")
    public ResponseEntity<Customer> getCustomer(@RequestParam(name = "idUser") Integer idUser) {
        Customer customer = customerService.findCustomer(idUser);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<IOrderHistory>> getOrderHistory(@RequestParam(name = "idUser") Integer idUser) {
        List<IOrderHistory> orderHistoryList = customerService.findHistory(idUser);
        return new ResponseEntity<>(orderHistoryList, HttpStatus.OK);
    }

}
