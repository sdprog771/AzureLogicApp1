package com.example.connecttologicapp1.service;

import com.example.connecttologicapp1.builder.RestTemplateBuilderObj;
import com.example.connecttologicapp1.domain.LogicAppItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {

    @Autowired
    @Qualifier("appRestClient")
    private RestTemplate restTemplate1;

    private String urlPart1 = "https://prod-45.northeurope.logic.azure.com/workflows/82eb35a7b32d485c9ff822f6d20df403/triggers/When_a_HTTP_request_is_received/paths/invoke/";
    private String urlPart2 = "?api-version=2016-10-01&sp=%2Ftriggers%2FWhen_a_HTTP_request_is_received%2Frun&sv=1.0&sig=IsoiPa-hSxcV_cGG0ieQcYYrvJOZzc1elUdOzvCBYuE";
    private String urlPart3 = "?api-version=2016-10-01&sp=/triggers/When_a_HTTP_request_is_received/run&sv=1.0&sig=IsoiPa-hSxcV_cGG0ieQcYYrvJOZzc1elUdOzvCBYuE";


    public String getItem(){
        RestTemplate restTemplate = new RestTemplate();
//        String responseJson = restTemplate.getForObject(urlPart1 + "book" + urlPart2, String.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("api-version", "2016-10-01");
        params.put("sp", "/triggers/When_a_HTTP_request_is_received/run");
        params.put("sv", "1.0");
        params.put("sig", "IsoiPa-hSxcV_cGG0ieQcYYrvJOZzc1elUdOzvCBYuE");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(urlPart1 + "book" + urlPart3, HttpMethod.GET, request, String.class, params);

        return response.getBody();
    }

    public String getItem2() throws JsonProcessingException, URISyntaxException {
//        String responseJson = restTemplate1.getForObject(urlPart1 + "book" + urlPart2,String.class);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(new URI(urlPart1 + "book"))
                .queryParam("api-version", "2016-10-01")
                .queryParam("sp", "/triggers/When_a_HTTP_request_is_received/run")
                .queryParam("sv", "1.0")
                .queryParam("sig", "IsoiPa-hSxcV_cGG0ieQcYYrvJOZzc1elUdOzvCBYuE");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate1.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);


        ObjectMapper mapper = new ObjectMapper();
        LogicAppItem item = mapper.readValue(response.getBody(), LogicAppItem.class);
        return item.getStatus();
    }


}
