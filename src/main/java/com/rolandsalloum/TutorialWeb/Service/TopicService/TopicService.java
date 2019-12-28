package com.rolandsalloum.TutorialWeb.Service.TopicService;

import com.rolandsalloum.TutorialWeb.Models.Topic;
import com.rolandsalloum.TutorialWeb.Repository.TopicRepository.ITopicRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements ITopicService {

    private ITopicRepositoryDAO iTopicRepositoryDAO;

    @Autowired
    public TopicService(ITopicRepositoryDAO iTopicRepositoryDAO) {
        this.iTopicRepositoryDAO = iTopicRepositoryDAO;
    }


    @Override
    public Topic createTopic(Topic topic) throws FailedToCreateTopic {
        try {
            return iTopicRepositoryDAO.save(topic);
        } catch (Exception e) {
            throw new FailedToCreateTopic("Failed To Create A Topic !");
        }
    }

    @Override
    public int deleteTopicById(int topicId) throws FailedToDeleteTopic {
        try {
            iTopicRepositoryDAO.deleteById(topicId);
            return topicId;
        } catch (Exception e) {
            throw new FailedToDeleteTopic("Failed To Delete Topic !");
        }
    }

    @Override
    public Topic updateTopicById(Topic topic, int topicId) throws FailedToUpdateTopic {
        try {
            Topic topicToBeUpdated = iTopicRepositoryDAO.getOne(topicId);
            topicToBeUpdated.setTopicId(topicId);
            topicToBeUpdated.setTopicDescription(topic.getTopicDescription());
            topicToBeUpdated.setTopicName(topic.getTopicName());
            iTopicRepositoryDAO.save(topicToBeUpdated);
            return topicToBeUpdated;
        } catch (Exception e) {
            throw new FailedToUpdateTopic("Failed To Update A Topic");
        }
    }

    @Override
    public List<Topic> getAllTopics() throws FailedtoFindTopics {
        try {
            List<Topic> topicList = iTopicRepositoryDAO.findAll();

            return topicList;
        } catch (Exception e) {
            throw new FailedtoFindTopics("Instructor Not Found !");
        }
    }

    @Override
    public Topic findTopicById(int topicId) throws FailedtoFindTopics {
        try {
            Topic topic = iTopicRepositoryDAO.getOne(topicId);

            if (topic.equals(null))
                throw new FailedtoFindTopics("Topic Not Found !");
            return topic;
        } catch (Exception e) {
            throw new FailedtoFindTopics("Topic Not Found !");
        }
    }

}
