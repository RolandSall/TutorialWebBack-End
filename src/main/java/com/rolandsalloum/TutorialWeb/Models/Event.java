package com.rolandsalloum.TutorialWeb.Models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
@Entity

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;
    @ManyToOne
    private Instructor instructor;
    @ManyToOne
    private Topic topic;
    @Column(nullable = false)
    private String eventName;
    @Column(nullable = false)
    private String eventDescription;
    @Column(nullable = false)
    private Date eventDate;
    @Column(nullable = false)
    private Date eventTime;
    @Column(nullable = false)
    private String eventLocation;
    @Column(nullable = false)
    private String eventCity;
    @Column(nullable = false)
    private String eventImageUrl;
    @Column(nullable = false)
    private String eventLevel;

    @ManyToMany(mappedBy = "events")
    @Column(name = "customerId")
    private List<Customer> customers;

}
