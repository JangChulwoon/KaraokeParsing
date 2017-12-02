package org.karaoke;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import org.karaoke.converter.CategoryConverter;
import org.karaoke.converter.CompanyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling
@ComponentScan("org.karaoke")
public class KaraokeApplication {

    @Autowired
    FormattingConversionService conversion;

    public static void main(String[] args) {
        SpringApplication.run(KaraokeApplication.class, args);
    }

    // 이게 왜이러지 ?  bean이 이름 매칭인가 ?
    @Bean
    public FormattingConversionService mvcConversionService() {
        conversion.addConverter(new CategoryConverter());
        conversion.addConverter(new CompanyConverter());
        return conversion;
    }

    @Bean
    public ExecutorService es() {
        return Executors.newCachedThreadPool(new CustomizableThreadFactory("Customize-Thread "));
    }

    @Bean
    public Cache<String, PreparsedDocumentEntry> buildparsedDocumentCache() {
        return Caffeine.newBuilder().maximumSize(1000).build();
    }

    @Bean
    public TaskScheduler taskScheduler() { // 단일 스레드로 돌아가는 scheduler
        return new ConcurrentTaskScheduler();
    }

}
