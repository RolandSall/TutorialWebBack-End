package com.rolandsalloum.TutorialWeb.Repository.InstructorRepository;

import com.rolandsalloum.TutorialWeb.Models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInstructorRepositoryDAO extends JpaRepository<Instructor,Integer> {
}
