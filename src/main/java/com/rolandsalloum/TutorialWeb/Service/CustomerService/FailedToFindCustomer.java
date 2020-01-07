package com.rolandsalloum.TutorialWeb.Service.CustomerService;

public class FailedToFindCustomer extends Throwable {
    public FailedToFindCustomer(String s) {
        super(s);
    }
}
