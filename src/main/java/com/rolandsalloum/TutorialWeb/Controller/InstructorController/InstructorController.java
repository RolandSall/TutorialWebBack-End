package com.rolandsalloum.TutorialWeb.Controller.InstructorController;

import com.rolandsalloum.TutorialWeb.Models.Instructor;
import com.rolandsalloum.TutorialWeb.Service.InstructorService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/home/instructors")
    public ResponseEntity findAllInstrcutors(){
        try {
            List<Instructor> instructorList = instructorService.findAllInstructors();
            List<InstructorApiResponse> responseList = buildResponseForInstructors(instructorList);
            return  ResponseEntity.status(HttpStatus.OK).body(responseList);
        } catch (FailedToFindInstructor failedToFindInstructor) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedToFindInstructor.getLocalizedMessage());
        }
    }


    @GetMapping("/home/instructors/{instructorId}")
    public ResponseEntity findInstructorById(@PathVariable("instructorId") int instructorId){
        try {
            Instructor instructor = instructorService.findInstructorByIdInstructors(instructorId);
            InstructorApiResponse responseList = getInstructorApiResponce(instructor);
            return  ResponseEntity.status(HttpStatus.OK).body(responseList);
        } catch (FailedToFindInstructor failedToFindInstructor) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedToFindInstructor.getLocalizedMessage());
        }

    }




    @PostMapping("/home/instructors")
    public ResponseEntity createInstructor(@RequestBody InstrocutorApiRequest request) {
        try {
            Instructor instructor = instructorService.createInstructor(getInstructor(request));
            InstructorApiResponse response = getInstructorApiResponce(instructor);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToCreateInstructor failedToCreateInstructor) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToCreateInstructor.getLocalizedMessage());
        }
    }


    @DeleteMapping("/home/instructors/{instructorId}")
    public ResponseEntity deleteInstructorById(@PathVariable("instructorId") int instructorId) {
        try {
            int instructorIdDeleted = instructorService.deleteInstructorById(instructorId);
            return ResponseEntity.status(HttpStatus.OK).body(instructorIdDeleted);
        } catch (FailedToDeleteInstructor failedToDeleteInstructor) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToDeleteInstructor.getLocalizedMessage());
        }


    }


    @PutMapping("/home/instructors/{instructorId}")
    public ResponseEntity updateInstructorById(@PathVariable("instructorId") int instructorId,
                                               @RequestBody InstrocutorApiRequest request ){

        try {
            Instructor instructor = instructorService.updateInstructorById(getInstructor(request),instructorId);
            InstructorApiResponse response = getInstructorApiResponce(instructor);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToUpdateInstructor failedToUpdateInstructor) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToUpdateInstructor.getLocalizedMessage());
        }


    }

    private List<InstructorApiResponse> buildResponseForInstructors(List<Instructor> instructorList) {
        List<InstructorApiResponse> responseList = new ArrayList<>();
        for(Instructor instructor: instructorList)
            responseList.add(getInstructorApiResponce(instructor));
        return  responseList;
    }


    private InstructorApiResponse getInstructorApiResponce(Instructor instructor) {
        return new InstructorApiResponse().builder()
                .instructorId(instructor.getInstructorId())
                .instructorFname(instructor.getInstructorFname())
                .instructorLname(instructor.getInstructorLname())
                .instructorAddress(instructor.getInstructorAddress())
                .gender(instructor.getGender())
                .instructorNationality(instructor.getInstructorNationality())
                .build();
    }

    private Instructor getInstructor(InstrocutorApiRequest request) {
        return new Instructor().builder()
                .instructorFname(request.getInstructorFname())
                .instructorLname(request.getInstructorLname())
                .instructorAddress(request.getInstructorAddress())
                .gender(request.getGender())
                .instructorNationality(request.getInstructorNationality())
                .build();
    }

}
