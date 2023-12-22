package com.example.cosmetic.controller;

import com.example.cosmetic.model.customer.Customer;
import com.example.cosmetic.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {
@Autowired
    private IOrderService orderService;
    @PostMapping("/payment")
    public ResponseEntity<Object> paymentOrder(@RequestParam(name = "idUser") Integer idUser){
        try {

            orderService.createOrder(idUser);
            orderService.createOrderDetail(idUser);
            orderService.updateTotalMoney(idUser);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Thanh toán thành công.",HttpStatus.OK);
    }
}
