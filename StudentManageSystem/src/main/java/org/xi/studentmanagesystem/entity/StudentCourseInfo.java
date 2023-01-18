package org.xi.studentmanagesystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.xi.studentmanagesystem.entity.pk.StudentCoursePK;

@Data
@Entity
@IdClass(StudentCoursePK.class)
public class StudentCourseInfo {

    @Id
    private Integer sid;
    @Id
    private Integer tcid;
    private String sname;

    private  Integer tid;
    private String tname;

    private String cname;

    private Integer credit;

    private Integer score;


}
