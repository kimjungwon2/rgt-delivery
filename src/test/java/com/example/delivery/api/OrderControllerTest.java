package com.example.delivery.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.delivery.api.request.OrderCreateRequest;
import com.example.delivery.config.auth.SecurityConfig;
import com.example.delivery.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = OrderController.class,
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                    classes = SecurityConfig.class)})
@WithMockUser(roles = "USER")
class OrderControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OrderService orderService;



    @DisplayName("주문을 한다.")
    @Test
    void createOrder() throws Exception{
        //given
        OrderCreateRequest request =OrderCreateRequest.builder()
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

        //when //then
        mockMvc.perform(post("/api/order")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("주문을 할 때 수량은 1 이상이여야 한다.")
    @Test
    void orderQuantityThanOne() throws Exception{
        //given
        OrderCreateRequest request =OrderCreateRequest.builder()
                .order_id(0007L)
                .product_name("카페테리아")
                .options("")
                .table_no(3)
                .quantity(0)
                .order_date(LocalDate.now())
                .order_time(LocalTime.now())
                .date_time("2023-07-17 17:33:31")
                .robot_status("")
                .dong("120")
                .ho("1701")
                .seq("23071701000074")
                .orderer_name("홍O동")
                .build();

        //when //then
        mockMvc.perform(post("/api/order")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("BAD REQUEST"))
                .andExpect(jsonPath("$.message").value("수량은 양수여야 합니다."));
    }

    @DisplayName("주문을 할 때 상품명은 필수이다.")
    @Test
    void orderByProductName() throws Exception{
        //given
        OrderCreateRequest request =OrderCreateRequest.builder()
                .order_id(0007L)
                .product_name(" ")
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

        //when //then
        mockMvc.perform(post("/api/order")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("BAD REQUEST"))
                .andExpect(jsonPath("$.message").value("상품 이름은 필수입니다."));
    }

    @DisplayName("모든 카페테리아 상품명을 카페라떼로 변경한다.")
    @Test
    void orderCafeteria() throws Exception {

      //given //when //then
        mockMvc.perform(put("/api/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

}