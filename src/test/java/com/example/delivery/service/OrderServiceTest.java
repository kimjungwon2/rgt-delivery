package com.example.delivery.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.delivery.api.request.OrderCreateRequest;
import com.example.delivery.domain.Order;
import com.example.delivery.repository.OrderRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @AfterEach
    void tearDown(){
        orderRepository.deleteAllInBatch();
    }

    @DisplayName("주문을 한다.")
    @Transactional
    @Test
    void createOrder(){
        //given
        OrderCreateRequest request = OrderCreateRequest.builder()
                .order_id(0007L)
                .product_name("카페모카")
                .options("")
                .table_no(3)
                .quantity(1)
                .order_date(LocalDate.now())
                .order_time(LocalTime.now())
                .date_time("2023-07-17 17:33:31")
                .robot_status("")
                .dong("120")
                .ho("1701")
                .seq("23071701000074")
                .orderer_name("홍O동")
                .build();

        //when
        String result = orderService.createOrder(request);

        //then
        assertThat(result).isEqualTo("주문번호 : 0007  : 수신");

        Order foundOrder =orderRepository.findById(0007L).orElseThrow();

        assertThat(request.getOrder_id()).isEqualTo(foundOrder.getId());
        assertThat(request.getProduct_name()).isEqualTo(foundOrder.getProductName());
        assertThat(request.getOptions()).isEqualTo(foundOrder.getOptions());
        assertThat(request.getTable_no()).isEqualTo(foundOrder.getTableNo());
        assertThat(request.getQuantity()).isEqualTo(foundOrder.getQuantity());
        assertThat(request.getRobot_status()).isEqualTo(foundOrder.getRobotStatus());
        assertThat(request.getDong()).isEqualTo(foundOrder.getDong());
        assertThat(request.getHo()).isEqualTo(foundOrder.getHo());
        assertThat(request.getSeq()).isEqualTo(foundOrder.getSeq());
        assertThat(request.getOrderer_name()).isEqualTo(foundOrder.getOrdererName());
    }

    @DisplayName("카페테리아 상품명으로 주문을 하면 카페라떼로 변경된다.")
    @Transactional
    @Test
    void createOrderByCafeteria(){
        //given
        OrderCreateRequest request = OrderCreateRequest.builder()
                .order_id(0007L)
                .product_name("카페테리아")
                .options("")
                .table_no(3)
                .quantity(1)
                .order_date(LocalDate.now())
                .order_time(LocalTime.now())
                .date_time("2023-07-17 17:33:31")
                .robot_status("")
                .dong("120")
                .ho("1701")
                .seq("23071701000074")
                .orderer_name("홍O동")
                .build();

        //when
        String result = orderService.createOrder(request);

        //then
        Order foundOrder =orderRepository.findById(0007L).orElseThrow();
        assertThat(foundOrder.getProductName()).isEqualTo("카페라떼");
    }


    @DisplayName("카페테리아를 카페라떼 이름으로 변경한다.")
    @Test
    void modifyAllProductName(){
        //given
        Order order1 = Order.builder()
                .id(0007L)
                .productName("카페테리아")
                .options("")
                .tableNo(3)
                .quantity(1)
                .orderDate(LocalDate.now())
                .orderTime(LocalTime.now())
                .dateTime(LocalDateTime.now())
                .robotStatus("")
                .dong("120")
                .ho("1701")
                .seq("23071701000074")
                .ordererName("홍O동")
                .build();

        Order order2 = Order.builder()
                .id(0002L)
                .productName("카페테리아")
                .options("")
                .tableNo(3)
                .quantity(1)
                .orderDate(LocalDate.now())
                .orderTime(LocalTime.now())
                .dateTime(LocalDateTime.now())
                .robotStatus("")
                .dong("120")
                .ho("1701")
                .seq("23071701000074")
                .ordererName("홍O동")
                .build();

        Order order3 = Order.builder()
                .id(0003L)
                .productName("카페테리아")
                .options("")
                .tableNo(3)
                .quantity(1)
                .orderDate(LocalDate.now())
                .orderTime(LocalTime.now())
                .dateTime(LocalDateTime.now())
                .robotStatus("")
                .dong("120")
                .ho("1701")
                .seq("23071701000074")
                .ordererName("홍O동")
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        //when
        orderService.modifyAllProductName();

        //then
        Order findOrder1 = orderRepository.findById(0007L)
                .orElseThrow(IllegalStateException::new);

        Order findOrder2 = orderRepository.findById(0002L)
                .orElseThrow(IllegalStateException::new);

        Order findOrder3 = orderRepository.findById(0003L)
                .orElseThrow(IllegalStateException::new);

        assertThat(findOrder1.getProductName()).isEqualTo("카페라떼");
        assertThat(findOrder2.getProductName()).isEqualTo("카페라떼");
        assertThat(findOrder3.getProductName()).isEqualTo("카페라떼");

    }

}