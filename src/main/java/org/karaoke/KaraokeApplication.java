package org.karaoke;

import org.karaoke.converter.CategoryConverter;
import org.karaoke.converter.CompanyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan("org.karaoke")
public class KaraokeApplication {

    @Autowired
    FormattingConversionService conversion;

    // 이게 왜이러지 ?  bean이 이름 매칭인가 ?
    @Bean
    public FormattingConversionService mvcConversionService() {
        conversion.addConverter(new CategoryConverter());
        conversion.addConverter(new CompanyConverter());
        return conversion;
    }

    @Bean
    public ExecutorService es(){
        ExecutorService es = Executors.newCachedThreadPool(new CustomizableThreadFactory("Customize-Thread "));
        return es;

    }


    public static void main(String[] args) {
        SpringApplication.run(KaraokeApplication.class, args);
    }

}
