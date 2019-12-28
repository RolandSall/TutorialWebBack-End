package com.rolandsalloum.TutorialWeb.Service.InstructorService;

import com.rolandsalloum.TutorialWeb.Models.Instructor;

import java.util.List;

public interface IInstructorService {

    Instructor createInstructor(Instructor instructor) throws FailedToCreateInstructor;

    int deleteInstructorById(int instructorId) throws FailedToDeleteInstructor;

    Instructor updateInstructorById(Instructor instructor, int instructorId) throws FailedToUpdateInstructor;

    List<Instructor> findAllInstructors() throws FailedToFindInstructor;

    Instructor findInstructorByIdInstructors(int instructorId) throws FailedToFindInstructor;
}
