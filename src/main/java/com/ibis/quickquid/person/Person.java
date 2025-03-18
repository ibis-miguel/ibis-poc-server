package com.ibis.quickquid.person;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

//    @Column(nullable = false, length = 100, unique = true)
//    private String email;

//    @Column(nullable = true)
//    private Date dateOfBirth;
}
