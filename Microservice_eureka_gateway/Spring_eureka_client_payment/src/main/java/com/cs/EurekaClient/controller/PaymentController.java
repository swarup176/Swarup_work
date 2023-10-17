package com.cs.EurekaClient.controller;


import com.cs.EurekaClient.entity.Payment;
import com.cs.EurekaClient.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) {
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId){
        try {
            return service.findPaymentHistoryByOrderId(orderId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
