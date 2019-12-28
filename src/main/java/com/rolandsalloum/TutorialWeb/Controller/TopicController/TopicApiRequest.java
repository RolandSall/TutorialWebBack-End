package com.rolandsalloum.TutorialWeb.Controller.TopicController;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
@Entity
public class TopicApiRequest {
    private String topicName;
    private String topicDescription;
}
