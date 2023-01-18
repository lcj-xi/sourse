package org.xi.studentmanagesystem.inerceptor;

public enum GlobalConst {
    ADMIN_SESSION_KEY("admin"),

    STUDENT_SESSION_KEY("student"),

    TEACHER_SESSION_KEY("teacher"),

    ID_SESSION_KEY("id");

    private String msg;
    GlobalConst(String msg){
        this.msg=msg;
    }


    public String getMsg(){
        return msg;
    }

}
