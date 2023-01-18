package org.xi.studentmanagesystem.inerceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/errors","/error");
    }
}
