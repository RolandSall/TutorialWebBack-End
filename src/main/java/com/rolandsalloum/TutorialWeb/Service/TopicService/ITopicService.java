package com.rolandsalloum.TutorialWeb.Service.TopicService;

import com.rolandsalloum.TutorialWeb.Models.Topic;

import java.util.List;

public interface ITopicService {
    Topic createTopic(Topic topic) throws FailedToCreateTopic;

    int deleteTopicById(int topicId) throws FailedToDeleteTopic;

    Topic updateTopicById(Topic topic, int topicId) throws FailedToUpdateTopic;

    List<Topic> getAllTopics() throws FailedtoFindTopics;

    Topic findTopicById(int topicId) throws FailedtoFindTopics;
}
