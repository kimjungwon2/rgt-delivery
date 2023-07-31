package com.example.delivery.service;

import com.example.delivery.api.request.OrderCreateRequest;
import com.example.delivery.domain.Order;
import com.example.delivery.repository.OrderRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public String createOrder(OrderCreateRequest request){

        LocalDateTime dateTime = ConvertStringToLocalDateTime(request);
        renameCafeteria(request);

        Order order = Order.builder().
                id(request.getOrder_id())
                .productName(request.getProduct_name())
                .options(request.getOptions())
                .tableNo(request.getTable_no())
                .quantity(request.getQuantity())
                .orderDate(request.getOrder_date())
                .orderTime(request.getOrder_time())
                .dateTime(dateTime)
                .robotStatus(request.getRobot_status())
                .dong(request.getDong())
                .ho(request.getHo())
                .seq(request.getSeq())
                .ordererName(request.getOrderer_name())
                .build();

        orderRepository.save(order);

        return String.format("주문번호 : %04d  : 수신",request.getOrder_id());
    }

    @Transactional
    public void modifyAllProductName(){
        orderRepository.modifyAllProductName("카페라떼","카페테리아");
    }

    private void renameCafeteria(OrderCreateRequest request) {
        if (request.getProduct_name().equals("카페테리아")){
            request.modifyProductName("카페라떼");
        }
    }

    private LocalDateTime ConvertStringToLocalDateTime(OrderCreateRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(request.getDate_time(),formatter);

        return dateTime;
    }


}
