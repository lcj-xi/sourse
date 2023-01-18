package org.xi.studentmanagesystem.entity.pk;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentCoursePK implements Serializable {
    Integer sid;
    Integer tcid;


}
