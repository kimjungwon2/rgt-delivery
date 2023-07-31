package com.example.delivery.api.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotNull(message = "주문 번호는 필수입니다.")
    private Long order_id;

    @NotNull(message = "상품 이름은 필수입니다.")
    private String productName;

    private String options;

    private Integer table_no;

    @Positive(message = "수량은 양수여야 합니다.")
    private Integer quantity;

    private LocalDate orderDate;

    private LocalTime orderTime;

    private LocalDateTime dateTime;

    private String robotStatus;

    private String dong;

    private String ho;

    private String seq;

    private String ordererName;

}
