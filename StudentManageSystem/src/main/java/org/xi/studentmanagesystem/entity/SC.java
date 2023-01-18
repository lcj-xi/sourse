package org.xi.studentmanagesystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.xi.studentmanagesystem.entity.pk.StudentCoursePK;

@Data
@Entity
@IdClass(StudentCoursePK.class)
public class SC {
    @Id
    private Integer sid;
    @Id
    private Integer tcid;

    private Integer score;

}
