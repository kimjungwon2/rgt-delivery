package com.example.delivery.service;

import com.example.delivery.api.request.OrderCreateRequest;
import com.example.delivery.domain.Order;
import com.example.delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public StringBuffer createOrder(OrderCreateRequest request){
        Order order = Order.builder().
                id(request.getOrder_id())
                .productName(request.getProductName())
                .options(request.getOptions())
                .table_no(request.getTable_no())
                .quantity(request.getQuantity())
                .orderDate(request.getOrderDate())
                .orderTime(request.getOrderTime())
                .dateTime(request.getDateTime())
                .robotStatus(request.getRobotStatus())
                .dong(request.getDong())
                .ho(request.getHo())
                .seq(request.getSeq())
                .ordererName(request.getOrdererName())
                .build();

        orderRepository.save(order);

        StringBuffer result = new StringBuffer();

        result.append("주문번호 : ");
        result.append(request.getOrder_id());
        result.append(" : 수신");

        return result;
    }

}
