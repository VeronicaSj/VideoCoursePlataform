package videocurseapp.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import videocurseapp.demo.Model.Course;

@Repository()
public interface CourseRepository extends CrudRepository<Course, Long> {

}