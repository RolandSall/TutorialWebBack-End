package com.rolandsalloum.TutorialWeb.Service.CustomerService;

public class FailedToDeleteCustomer extends Throwable {
    public FailedToDeleteCustomer(String s) {
        super(s);
    }
}
