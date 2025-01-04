package com.example.connecttologicapp1.controller;

import com.example.connecttologicapp1.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{item}")
    public void meth(@PathVariable String item) {

    }

    @GetMapping("/logicApp")
    public void callLogicApp() {
        itemService.getItem();
    }

    @GetMapping("/logicApp2")
    public ResponseEntity<String> callLogicApp2() throws JsonProcessingException, URISyntaxException {
        return new ResponseEntity<>(itemService.getItem2(), HttpStatus.OK);
//        return new ResponseEntity(itemService.getItem2());
    }
}
