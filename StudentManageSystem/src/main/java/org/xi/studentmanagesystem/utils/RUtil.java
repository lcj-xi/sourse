package org.xi.studentmanagesystem.utils;


import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.vo.R;

/**
 * 对R对象进行简化
 */
public class RUtil {
    public static R sucess(Object obj){
        R result = new R();
        REnum sucess = REnum.SUCESS;
        result.setCode(sucess.getCode());
        result.setMsg(sucess.getMsg());
        result.setData(obj);
        return result;
    }
    public static R sucess(){
        return sucess(null);
    }

    public static R error(Integer code , String msg){

        R result = new R();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static R error(REnum rEnum){
        return error(rEnum.getCode(),rEnum.getMsg());
    }

}
