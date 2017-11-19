package org.karaoke;

import lombok.extern.slf4j.Slf4j;
import org.json.HTTP;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class KaraokeApplicationTests {

    static RestTemplate rest = new RestTemplate();
    static String query  = "{\"query\": \"query selectKaraoke($karaoke: karaoke, $page : Int){\\n  Karaoke(karaoke: $karaoke , page : $page){\\n    number\\n    title\\n    singer\\n  }\\n}\",\"variables\":{\"karaoke\":{\"company\":\"TJ\",\"category\":\"NUMBER\",\"keyword\":\"100\"},\"page\":4},\"operationName\":\"selectKaraoke\"}";
	public static void main(String args[]) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(query, headers);
        ExecutorService es = Executors.newFixedThreadPool(50);
        for(int i = 0; i<50; ++i){
            Callable<String> future =  ()->{
                String str = rest.postForObject("http://localhost:8080/karaokeGraphQL",entity,String.class);
                System.out.println(str);
                return str;
            };
            es.submit(future);
        }
    }

}
