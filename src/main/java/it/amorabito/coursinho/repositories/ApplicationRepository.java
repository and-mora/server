package it.amorabito.coursinho.repositories;

import it.amorabito.coursinho.model.entities.Application;
import it.amorabito.coursinho.model.entities.CourseEdition;
import it.amorabito.coursinho.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

//    @Query("select app from Application app where app.edition = :editionId")
    Collection<Application> findByEdition(CourseEdition courseEdition);

    Collection<Application> findByStudent(Student student);
}
