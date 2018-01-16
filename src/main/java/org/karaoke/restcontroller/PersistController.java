package org.karaoke.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Stream;

@RestController
@Slf4j
public class PersistController {


    @Autowired
    Map<String,String> persistentQuery;

    @GetMapping("/queries")
    public String selectAll(){ // key : value 인 json 으로 추후 바꿀 것
        return persistentQuery.toString();
    }

    @PostMapping("/queries")
    public ResponseEntity<String> selectAll(@RequestBody Map<String,String> request){
        persistentQuery.put(request.get("key"),request.get("query"));
        return ResponseEntity.ok("Success");
    }

}
