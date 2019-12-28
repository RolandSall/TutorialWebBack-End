package com.rolandsalloum.TutorialWeb.Controller.InstructorController;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class InstrocutorApiRequest {

    private String instructorFname;
    private String instructorLname;
    private String gender;
    private String instructorNationality;
    private String instructorAddress;

}
