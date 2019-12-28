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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    @Column(nullable = false)
    private String customerUsername;
    @Column(nullable = false)
    private String customerPassword;
    @Column(nullable = false)
    private String customerAddress;
    @Column(nullable = false)
    private String customerGender;
    @Column(nullable = false)
    private String CustomerFname;
    @Column(nullable = false)
    private String CustomerLname;

    @ManyToMany
    @Column(name = "eventId")
    private List<Event> events;


}
