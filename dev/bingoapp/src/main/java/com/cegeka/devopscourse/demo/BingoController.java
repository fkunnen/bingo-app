package com.cegeka.devopscourse.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/bingo/items")
public class BingoController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addItem(@RequestBody @Valid Item newItem) {
        itemService.saveItem(newItem);
        URI uri = UriComponentsBuilder.fromPath("/bingo/items/" + newItem.getId())
                .build()
                .encode()
                .toUri();
        return created(uri).build();
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getItems() {
        return ok().body(itemService.findAllItems());
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteItem(@PathVariable("id") String itemId) {
        itemService.delete(itemId);
        return ResponseEntity.ok().build();
    }

}
