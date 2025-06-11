package videocurseapp.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import videocurseapp.demo.Model.Course;

@Repository()
public interface CourseRepository extends CrudRepository<Course, Long> {
    @Query("SELECT e FROM Course e WHERE e.name LIKE %:name%")
    List<Course> findByNameCustom(@Param("name") String name);

}