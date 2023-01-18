package org.xi.studentmanagesystem.entity.pk;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TeacherCoursePK implements Serializable {
    Integer tid;
    Integer cid;
}
