package org.xi.studentmanagesystem.inerceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TeacherWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(new TeacherInterceptor())
                .addPathPatterns("/teacher/**").excludePathPatterns("/teacher/login","errors","/error");
    }
}
