package com.rolandsalloum.TutorialWeb.Controller.CustomerController;

import lombok.*;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CustomerApiResponse {
    private int customerId;
    private String customerUsername;
    private String customerPassword;
    private String customerAddress;
    private String customerGender;
    private String CustomerFname;
    private String CustomerLname;
}
