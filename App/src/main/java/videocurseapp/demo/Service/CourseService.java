package videocurseapp.demo.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Model.Video;
import videocurseapp.demo.Repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course save(Course c){
        Course res = null;
        if(c!=null){
            res=courseRepository.save(c);
        }
        return res;
    }

    public boolean delete(Course c){
        boolean res = false;
        if(c!=null){
            courseRepository.delete(c);
            res=true;
        }
        return res;
    }
    
    public ArrayList<Video> listVideos(Course course) {
        ArrayList<Video> res = null;
        
        if(course!=null && course.getVideos()!=null){
            res =new ArrayList<Video>(course.getVideos());
    
            res.sort(new Comparator<Video>() {
                @Override
                public int compare(Video p1, Video p2) {
                    return p1.getTitle().compareTo(p2.getTitle());
                }
            });
        }
        System.out.println(res);
        return res;
    }

    public Course find(long id){
        Course res=null;
        Optional opt = courseRepository.findById(id);
        if(opt.isPresent()){
            res=(Course)opt.get();
        }
        return res;
    }

    public ArrayList<Course> searchCourses(String search){
        ArrayList<Course> res = null;
        res= (ArrayList<Course>)courseRepository.findByNameCustom(search);
        return res;
    }
}
