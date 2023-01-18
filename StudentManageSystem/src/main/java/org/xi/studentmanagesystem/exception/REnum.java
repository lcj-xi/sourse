package org.xi.studentmanagesystem.exception;


/**
 * 关于异常的枚举类
 */
public enum REnum {
    SUCESS(0, "成功！"),
    UNKNOW_ERROR(-999, "未知错误！"),
    COMMON_ERROR(-10, "一般性错误！"),
    SESSION_ERROR(-1,"无权访问。"),
    LOGIN_ERROR(-2, "登录出错，不正确的密码或用户！"),
    INSERT_ERROR(-3,"新增失败。"),
    UPDATE_ERROR(-4,"更新失败，该用户不存在。"),
    DELETE_ERROR(-5,"删除失败，该用户不存在"),
    SELECT_ERROR(-6,"查询错误");



    private Integer code;
    private String msg;

    REnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
