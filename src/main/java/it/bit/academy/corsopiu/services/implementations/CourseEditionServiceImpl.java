package it.bit.academy.corsopiu.services.implementations;

import it.bit.academy.corsopiu.entities.Module;
import it.bit.academy.corsopiu.entities.*;
import it.bit.academy.corsopiu.exceptions.EntityNotFoundException;
import it.bit.academy.corsopiu.repositories.*;
import it.bit.academy.corsopiu.services.abstractions.CourseEditionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class CourseEditionServiceImpl implements CourseEditionService {

    private CourseEditionRepository courseEditionRepo;
    private CourseRepository courseRepo;
    private PersonRepository personRepo;
    private ModuleRepository moduleRepo;
    private ClassroomRepository classroomRepo;

    @Autowired
    public CourseEditionServiceImpl(CourseEditionRepository courseEditionRepo,
                                    CourseRepository courseRepo,
                                    PersonRepository personRepo,
                                    ModuleRepository moduleRepo,
                                    ClassroomRepository classroomRepo) {
        this.courseEditionRepo = courseEditionRepo;
        this.courseRepo = courseRepo;
        this.personRepo = personRepo;
        this.moduleRepo = moduleRepo;
        this.classroomRepo = classroomRepo;
    }

    @Override
    public Optional<CourseEdition> getCourseEditionById(long id) {
        return this.courseEditionRepo.findById(id);
    }

    @Override
    public Collection<Module> getModuleByCourseEditionId(long editionId) {
        return this.moduleRepo.getByCouseEditionId(editionId);
    }

    @Override
    public CourseEdition createCourseEdition(CourseEdition courseEdition) throws EntityNotFoundException {
        Optional<Person> tutor = personRepo.findById(courseEdition.getTutor().getId());
        if (tutor.isEmpty()) {
            throw new EntityNotFoundException("tutore con id" + courseEdition.getTutor().getId() + " non trovato");
        }
        courseEdition.setTutor(tutor.get());

        Optional<Classroom> classroom = classroomRepo.findById(courseEdition.getClassroom().getId());
        if (classroom.isEmpty()) {
            throw new EntityNotFoundException("classroom con id" + courseEdition.getClassroom().getId() + " non trovata");
        }
        courseEdition.setClassroom(classroom.get());

        Optional<Course> course = courseRepo.findById(courseEdition.getCourse().getId());
        if (course.isEmpty()) {
            throw new EntityNotFoundException("course con id" + courseEdition.getCourse().getId() + " non trovato");
        }
        courseEdition.setCourse(course.get());

        return this.courseEditionRepo.save(courseEdition);
    }
}
