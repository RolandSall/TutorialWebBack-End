package com.rolandsalloum.TutorialWeb.Models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int instructorId;
    @Column(nullable = false)
    private String instructorFname;
    @Column(nullable = false)
    private String instructorLname;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String instructorNationality;
    @Column(nullable = false)
    private String instructorAddress;


    @OneToMany(mappedBy = "instructor")
    @Column(name = "eventsId")
    private List<Event> events;
}
