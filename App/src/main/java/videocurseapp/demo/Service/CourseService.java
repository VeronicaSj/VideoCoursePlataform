package videocurseapp.demo.Service;

import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Model.Video;
import videocurseapp.demo.Repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public boolean save(Course c){
        boolean res = false;
        if(c!=null){
            courseRepository.save(c);
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
}
