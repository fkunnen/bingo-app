package com.cegeka.devopscourse.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/bingo/health")
public class BingoHealthController {

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getHealth(){
        return ok().body("Healthy");
    }

}
