package com.rolandsalloum.TutorialWeb.Service.InstructorService;

public class FailedToUpdateInstructor extends Throwable {
    public FailedToUpdateInstructor(String failed_to_update_instructor) {
        super(failed_to_update_instructor);
    }
}
