package videocurseapp.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    private Course tempCourse;

    public Course getTempCourse() {
        return tempCourse;
    }

    public void setTempCourse(Course tempCourse) {
        this.tempCourse = tempCourse;
    }
    
    
}
