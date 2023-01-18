package org.xi.studentmanagesystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tcid;

    private Integer tid;

    private Integer cid;

}