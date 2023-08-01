package com.example.delivery.api;

import com.example.delivery.api.request.OrderCreateRequest;
import com.example.delivery.api.request.OrderDTO;
import com.example.delivery.service.OrderService;
import com.example.delivery.service.RemoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RemoteOrderController {

    private final OrderService orderService;
    private final RemoteService remoteService;

    @PostMapping( "/api/remote/order")
    public String createOrder(@Valid @RequestBody OrderCreateRequest request)
            throws JsonProcessingException {
        return remoteService.postRemoteOrder(request);
    }

}
