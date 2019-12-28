package com.rolandsalloum.TutorialWeb.Service.InstructorService;

import com.rolandsalloum.TutorialWeb.Models.Instructor;
import com.rolandsalloum.TutorialWeb.Repository.InstructorRepository.IInstructorRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService implements IInstructorService {

    private IInstructorRepositoryDAO iInstructorRepositoryDAO;

    @Autowired
    public InstructorService(IInstructorRepositoryDAO iInstructorRepositoryDAO) {
        this.iInstructorRepositoryDAO = iInstructorRepositoryDAO;
    }

    @Override
    public Instructor createInstructor(Instructor instructor) throws FailedToCreateInstructor {

        try {
            return iInstructorRepositoryDAO.save(instructor);
        } catch (Exception e) {
            throw new FailedToCreateInstructor("Failed To Insert Instructor");
        }
    }

    @Override
    public int deleteInstructorById(int instructorId) throws FailedToDeleteInstructor {
        try {
            iInstructorRepositoryDAO.deleteById(instructorId);
            return instructorId;
        } catch (Exception e) {
            throw new FailedToDeleteInstructor("Failed To Delete Instructor");
        }
    }

    @Override
    public Instructor updateInstructorById(Instructor instructor, int instructorId) throws FailedToUpdateInstructor {
        try {
            Instructor instructorToBeUpdated = iInstructorRepositoryDAO.getOne(instructorId);
            instructorToBeUpdated.setInstructorFname(instructor.getInstructorFname());
            instructorToBeUpdated.setInstructorLname(instructor.getInstructorLname());
            instructorToBeUpdated.setGender(instructor.getGender());
            instructorToBeUpdated.setInstructorNationality(instructor.getInstructorNationality());
            instructorToBeUpdated.setInstructorAddress(instructor.getInstructorAddress());
            System.out.println(instructor.getInstructorAddress());
            instructorToBeUpdated.setInstructorId(instructorId);
            iInstructorRepositoryDAO.save(instructorToBeUpdated);
            return instructorToBeUpdated;
        } catch (Exception e) {
            throw new FailedToUpdateInstructor("Failed To Update Instructor");
        }


    }

    @Override
    public List<Instructor> findAllInstructors() throws FailedToFindInstructor {
        try {
            List<Instructor> instructors = iInstructorRepositoryDAO.findAll();
            System.out.println(instructors.size());
            return instructors;
        } catch (Exception e) {
            throw new FailedToFindInstructor("Instructor Not Found !");
        }
    }

    @Override
    public Instructor findInstructorByIdInstructors(int instructorId) throws FailedToFindInstructor {
        try {
            Instructor instructor = iInstructorRepositoryDAO.getOne(instructorId);
            System.out.println(instructor);
            if(instructor.equals(null))
                throw new FailedToFindInstructor("Instructor Not Found !");
            return instructor;
        } catch (Exception e) {
            throw new FailedToFindInstructor("Instructor Not Found !");
        }
    }
}
