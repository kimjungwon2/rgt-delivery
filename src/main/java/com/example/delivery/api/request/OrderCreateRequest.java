package com.example.delivery.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotNull(message = "주문 번호는 필수입니다.")
    private Long order_id;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String product_name;

    private String options;

    private Integer table_no;

    @Positive(message = "수량은 양수여야 합니다.")
    private Integer quantity;

    private LocalDate order_date;

    private LocalTime order_time;

    private String date_time;

    private String robot_status;

    private String dong;

    private String ho;

    private String seq;

    private String orderer_name;

    public void modifyProductName(String product_name) {
        this.product_name = product_name;
    }

    @Builder
    public OrderCreateRequest(Long order_id, String product_name, String options, Integer table_no,
            Integer quantity, LocalDate order_date, LocalTime order_time, String date_time,
            String robot_status, String dong, String ho, String seq, String orderer_name) {
        this.order_id = order_id;
        this.product_name = product_name;
        this.options = options;
        this.table_no = table_no;
        this.quantity = quantity;
        this.order_date = order_date;
        this.order_time = order_time;
        this.date_time = date_time;
        this.robot_status = robot_status;
        this.dong = dong;
        this.ho = ho;
        this.seq = seq;
        this.orderer_name = orderer_name;
    }
}
