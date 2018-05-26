package org.karaoke.config;

import org.karaoke.Interceptor.RefererChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class MVCConfig extends WebMvcConfigurerAdapter {

    RefererChecker refererChecker;

    @Autowired
    public MVCConfig(RefererChecker refererChecker) {
        this.refererChecker = refererChecker;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(refererChecker)
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/karaokeGraphiQL");
    }
}
