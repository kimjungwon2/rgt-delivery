package com.example.delivery.api.request;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class OrderDTO {

    private Long order_id;
    private String product_name;
    private String options;

    private Integer table_no;

    private Integer quantity;

    private String order_date;

    private String order_time;

    private String date_time;

    private String robot_status;

    private String dong;

    private String ho;

    private String seq;

    private String orderer_name;

}
