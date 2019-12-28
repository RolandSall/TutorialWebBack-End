package com.rolandsalloum.TutorialWeb.Controller.TopicController;

import com.rolandsalloum.TutorialWeb.Controller.InstructorController.InstructorApiResponse;
import com.rolandsalloum.TutorialWeb.Models.Instructor;
import com.rolandsalloum.TutorialWeb.Models.Topic;
import com.rolandsalloum.TutorialWeb.Service.TopicService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopicController {

    TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping("/home/topics")
    public ResponseEntity getAllTopics(){
        try {
            List<Topic> topicList = topicService.getAllTopics();
            List<TopicApiResponse> topicApiResponseList = buildApiReponseForTopics(topicList);
            return ResponseEntity.status(HttpStatus.OK).body(topicApiResponseList);
        } catch (FailedtoFindTopics failedtoFindTopics) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedtoFindTopics.getLocalizedMessage());
        }


    }

    @GetMapping("/home/topics/{topicId}")
    public ResponseEntity getTopicById(@PathVariable("topicId") int topicId){
        try {
            Topic topic = topicService.findTopicById(topicId);
            TopicApiResponse response = getTopicApiResponse(topic);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedtoFindTopics failedToFindInstructor) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedToFindInstructor.getLocalizedMessage());
        }

    }




    @PostMapping("/home/topics")
    public ResponseEntity createTopic(@RequestBody TopicApiRequest request) {
        try {
            Topic topic = topicService.createTopic(getTopic(request)) ;
            TopicApiResponse response = getTopicApiResponse(topic);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToCreateTopic failedToCreateTopic) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToCreateTopic.getLocalizedMessage());
        }

    }

    @DeleteMapping("/home/topics/{topicId}")
    public ResponseEntity deleteTopicById(@PathVariable("topicId") int topicId){
        try {
            int topicIdDeleted = topicService.deleteTopicById(topicId);
            return ResponseEntity.status(HttpStatus.OK).body(topicIdDeleted);
        } catch (FailedToDeleteTopic failedToDeleteTopic) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToDeleteTopic.getLocalizedMessage());


        }
    }

    @PutMapping("/home/topics/{topicId}")
        public ResponseEntity updateTopicById(@PathVariable("topicId") int topicId, @RequestBody TopicApiRequest request){
            try {
                Topic topic = topicService.updateTopicById(getTopic(request),topicId) ;
                TopicApiResponse response = getTopicApiResponse(topic);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (FailedToUpdateTopic failedToUpdateTopic) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToUpdateTopic.getLocalizedMessage());
            }

    }


    private List<TopicApiResponse> buildApiReponseForTopics(List<Topic> topicList) {
        List<TopicApiResponse> responseList = new ArrayList<>();
        for(Topic topic: topicList)
            responseList.add(getTopicApiResponse(topic));
        return  responseList;
    }



    private TopicApiResponse getTopicApiResponse(Topic topic) {
    return new TopicApiResponse().builder()
            .topicId(topic.getTopicId())
            .topicName(topic.getTopicName())
            .topicDescription(topic.getTopicDescription())
            .build();
    }

    private Topic getTopic(TopicApiRequest request) {
        return new Topic().builder()
                .topicName(request.getTopicName())
                .topicDescription(request.getTopicDescription())
                .build();
    }
}
