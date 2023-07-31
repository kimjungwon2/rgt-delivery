package com.example.delivery.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @Column(name = "order_id")
    private Long id;

    private String productName;

    private String options;

    private Integer tableNo;

    private Integer quantity;

    private LocalDate orderDate;

    private LocalTime orderTime;

    private LocalDateTime dateTime;

    private String robotStatus;

    private String dong;

    private String ho;

    private String seq;

    private String ordererName;

    public void modifyProductName(String productName) {
        this.productName = productName;
    }

    @Builder
    public Order(Long id, String productName, String options, Integer tableNo, Integer quantity,
            LocalDate orderDate, LocalTime orderTime, LocalDateTime dateTime, String robotStatus,
            String dong, String ho, String seq, String ordererName) {
        this.id = id;
        this.productName = productName;
        this.options = options;
        this.tableNo = tableNo;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.dateTime = dateTime;
        this.robotStatus = robotStatus;
        this.dong = dong;
        this.ho = ho;
        this.seq = seq;
        this.ordererName = ordererName;
    }

}
