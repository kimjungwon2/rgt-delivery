package com.example.delivery.api;

import com.example.delivery.api.request.OrderCreateRequest;
import com.example.delivery.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/order")
    public String createOrder(@Valid @RequestBody OrderCreateRequest request){
        return orderService.createOrder(request);
    }

    @PutMapping("/api/modify")
    public void modifyCafeteria(){
        orderService.modifyAllProductName();
    }

}
