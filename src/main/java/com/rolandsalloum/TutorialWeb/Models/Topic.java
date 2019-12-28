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
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int topicId;
    @Column(nullable = false)
    private String topicName;
    @Column(nullable = false)
    private String topicDescription;


    @OneToMany(mappedBy = "topic")
    @Column(name = "eventsId")
    private List<Event> events;


}
