package com.example.delivery.service;


import com.example.delivery.api.request.OrderCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemoteService {

    public String postRemoteOrder(OrderCreateRequest request) {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = setUri();
        MultiValueMap<String, Object> parameters = getParameters(request);

        restTemplate.postForLocation(uri, request);

        return "OK";
    }


    private MultiValueMap<String, Object> getParameters(OrderCreateRequest request) {
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

        parameters.add("order_id", request.getOrder_id());
        parameters.add("product_name", request.getProduct_name());
        parameters.add("options", request.getOptions());
        parameters.add("table_no", request.getOptions());
        parameters.add("quantity", request.getQuantity());
        parameters.add("order_date", request.getOrder_date());
        parameters.add("order_time", request.getOrder_time());
        parameters.add("date_time", request.getDate_time());
        parameters.add("robot_status", request.getRobot_status());
        parameters.add("dong", request.getDong());
        parameters.add("ho", request.getHo());
        parameters.add("seq", request.getSeq());
        parameters.add("orderer_name", request.getOrderer_name());
        return parameters;
    }


    private URI setUri() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://211.44.24.167:9002/codingTest/post.php")
                .encode()
                .build()
                .toUri();
        return uri;
    }
}
