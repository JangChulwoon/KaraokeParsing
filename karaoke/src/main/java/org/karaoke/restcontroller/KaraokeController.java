package org.karaoke.restcontroller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KaraokeController {

    @GetMapping("/{company}/{category}/{word}")
    public String selectKaraoke(){
        log.info("Hello?? ");
        return "{ 'hello' : 'world'}";
    }

}
