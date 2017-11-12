package org.karaoke;

import org.karaoke.converter.CategoryConverter;
import org.karaoke.converter.CompanyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.support.FormattingConversionService;

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

    public static void main(String[] args) {
        SpringApplication.run(KaraokeApplication.class, args);
    }

}
