package com.rolandsalloum.TutorialWeb.Controller.TopicController;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter

public class TopicApiResponse {
    private int topicId;
    private String topicName;
    private String topicDescription;
}
