package it.amorabito.coursinho.model.mapper;

import it.amorabito.coursinho.model.dtos.CourseDto;
import it.amorabito.coursinho.model.dtos.CourseEditionPresentation;
import it.amorabito.coursinho.model.entities.Course;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDto courseDto);

    Collection<Course> toEntityList(Collection<CourseDto> courseDtos);

    CourseDto toDto(Course course);

    Collection<CourseDto> toDtoList(Collection<Course> courses);

    Collection<CourseEditionPresentation> toCourseEditionPresentation(Collection<CourseDto> courseDtos);
}
