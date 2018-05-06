package org.karaoke.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RefererChecker extends HandlerInterceptorAdapter {

    @Value("${permitted.url}")
    private String PERMITTED_URL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String referer = request.getHeader("referer");
        log.info("input referer :: {}", referer);
        return isPermitURL(referer) && super.preHandle(request, response, handler);
    }

    private boolean isPermitURL(String referer) {
        return PERMITTED_URL.equals(referer);
    }
}
