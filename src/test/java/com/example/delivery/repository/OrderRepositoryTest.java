package com.example.delivery.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import com.example.delivery.domain.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("원하는 상품명을 주문한다.")
    @Test
    void test(){
        //given
        Order order = Order.builder()
                .id(0007L)
                .productName("카페모카")
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

        //when
        orderRepository.save(order);
        Order foundOrder =orderRepository.findById(0007L).orElseThrow();

        //then
        assertThat(order.getId()).isEqualTo(foundOrder.getId());
        assertThat(order.getProductName()).isEqualTo(foundOrder.getProductName());
        assertThat(order.getOptions()).isEqualTo(foundOrder.getOptions());
        assertThat(order.getTableNo()).isEqualTo(foundOrder.getTableNo());
        assertThat(order.getQuantity()).isEqualTo(foundOrder.getQuantity());
        assertThat(order.getOrderDate()).isEqualTo(foundOrder.getOrderDate());
        assertThat(order.getOrderTime()).isEqualTo(foundOrder.getOrderTime());
        assertThat(order.getDateTime()).isEqualTo(foundOrder.getDateTime());
        assertThat(order.getRobotStatus()).isEqualTo(foundOrder.getRobotStatus());
        assertThat(order.getDong()).isEqualTo(foundOrder.getDong());
        assertThat(order.getHo()).isEqualTo(foundOrder.getHo());
        assertThat(order.getSeq()).isEqualTo(foundOrder.getSeq());
        assertThat(order.getOrdererName()).isEqualTo(foundOrder.getOrdererName());

    }

}