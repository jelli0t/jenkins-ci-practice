package com.yellesdev.jenkins_ci_practice.partners.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partners_id_gen")
    @SequenceGenerator(
            name = "partners_id_gen",
            sequenceName = "partners_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String no;
    private String name;
}
