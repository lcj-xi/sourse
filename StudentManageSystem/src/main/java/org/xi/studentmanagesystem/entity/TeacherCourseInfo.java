package org.xi.studentmanagesystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.xi.studentmanagesystem.entity.pk.TeacherCoursePK;

@Data
@Entity
@Table(name = "teacherCourseInfo")
@IdClass(TeacherCoursePK.class)
public class TeacherCourseInfo {


    @Id
    private Integer tid;

    @Id
    private Integer cid;

    private Integer tcid;

    private String tname;


    private String cname;

}
