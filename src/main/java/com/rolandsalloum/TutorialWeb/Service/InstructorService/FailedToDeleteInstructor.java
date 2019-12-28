package com.rolandsalloum.TutorialWeb.Service.InstructorService;

public class FailedToDeleteInstructor extends Throwable {
    public FailedToDeleteInstructor(String failed_to_delete_instructor) {
        super(failed_to_delete_instructor);
    }
}
