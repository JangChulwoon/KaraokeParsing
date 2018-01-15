package org.karaoke.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PersistController {


    @GetMapping("/admin")
    public ResponseEntity<String> addQuery(String query , String message){

        return ResponseEntity.ok("OK");
    }

}
