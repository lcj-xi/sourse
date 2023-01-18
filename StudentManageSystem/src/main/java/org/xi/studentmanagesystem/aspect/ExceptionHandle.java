package org.xi.studentmanagesystem.aspect;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.exception.RException;
import org.xi.studentmanagesystem.utils.RUtil;
import org.xi.studentmanagesystem.vo.R;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e){
        if(e instanceof RException){
            return RUtil.error(((RException) e).getCode(),e.getMessage());
        }
        else {
            return RUtil.error(REnum.UNKNOW_ERROR);
        }
    }
}
