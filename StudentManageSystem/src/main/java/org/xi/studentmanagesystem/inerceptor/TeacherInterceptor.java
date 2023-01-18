package org.xi.studentmanagesystem.inerceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class TeacherInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object handle)throws Exception{
        HttpSession session = httpServletRequest.getSession();
        String  code =(String) ((HttpSession) session).getAttribute(GlobalConst.TEACHER_SESSION_KEY.getMsg());
        if(code==null||code.equals("")){
            httpServletRequest.getRequestDispatcher("/errors").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        return true;

    }
}
