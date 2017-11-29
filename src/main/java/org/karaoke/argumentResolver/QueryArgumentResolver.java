package org.karaoke.argumentResolver;

import lombok.extern.slf4j.Slf4j;
import org.karaoke.domain.GraphQLQuery;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Component
@Slf4j
public class QueryArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return GraphQLQuery.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public GraphQLQuery resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req =  webRequest.getNativeRequest(HttpServletRequest.class);
        log.info("이거 ! {}", req.getAttribute("query"));
        return null;
    }


}
