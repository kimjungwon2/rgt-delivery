package com.example.delivery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.delivery.domain.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void tearDown(){
        orderRepository.deleteAllInBatch();
    }

    @DisplayName("원하는 상품명을 주문한다.")
    @Test
    void createOrder(){
        //given
        Order order = Order.builder()
                .id(0007L)
                .productName("카페모카")
                .options("")
                .tableNo(3)
                .quantity(1)
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
        assertThat(order.getRobotStatus()).isEqualTo(foundOrder.getRobotStatus());
        assertThat(order.getDong()).isEqualTo(foundOrder.getDong());
        assertThat(order.getHo()).isEqualTo(foundOrder.getHo());
        assertThat(order.getSeq()).isEqualTo(foundOrder.getSeq());
        assertThat(order.getOrdererName()).isEqualTo(foundOrder.getOrdererName());
    }

    @DisplayName("모든 상품명을 변경합니다.")
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
        orderRepository.modifyAllProductName("카페티아","카페테리아");

      //then

        Order findOrder1 = orderRepository.findById(0007L)
                .orElseThrow(IllegalStateException::new);

        Order findOrder2 = orderRepository.findById(0002L)
                .orElseThrow(IllegalStateException::new);

        Order findOrder3 = orderRepository.findById(0003L)
                .orElseThrow(IllegalStateException::new);

        assertThat(findOrder1.getProductName()).isEqualTo("카페티아");
        assertThat(findOrder2.getProductName()).isEqualTo("카페티아");
        assertThat(findOrder3.getProductName()).isEqualTo("카페티아");

    }


}