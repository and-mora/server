package it.bit.academy.corsopiu.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("STUD")
public class Student extends Person {

    public Student(String name, String surname, Gender gender) {

        super(name, surname, gender);

    }

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE})
    private List<Application> applications;


}
