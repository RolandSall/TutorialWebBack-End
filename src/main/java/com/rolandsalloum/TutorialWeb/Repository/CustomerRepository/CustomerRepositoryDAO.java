package com.rolandsalloum.TutorialWeb.Repository.CustomerRepository;

import com.rolandsalloum.TutorialWeb.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositoryDAO extends JpaRepository<Customer,Integer> {
}
