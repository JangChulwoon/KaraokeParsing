package org.karaoke.restcontroller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KaraokeController {

    @GetMapping("/{company}/{category}/{word}")
    public String selectKaraoke(@PathVariable String company, @PathVariable String category, @PathVariable String word){
        log.info("company : {} , category : {} , word : {}",company,category,word);
        return "{ 'hello' : 'world'}";
    }

}
