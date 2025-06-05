package videocurseapp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Service.VideoService;
import videocurseapp.demo.Utilities.StreamBytesInfo;


@Controller
public class VideoController {

    @Autowired 
    private VideoService videoService;
    @Autowired 
    UserService userService;
    
    private ControllerStaticParent parent = new ControllerStaticParent();

    
    @GetMapping("/videos/watch/{id}")
    public String watchVideo(Model model,
        @PathVariable long id) {
        User user = (User) userService.findInUseUser();
        model = parent.basicModelGenerator(user, model,  "Watch Video");
        model.addAttribute("videosrc", "/video/"+id);
        return "video_watch";
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<StreamingResponseBody> streamVideo(
            @RequestHeader(value = "Range", required = false) String httpRangeHeader,
            @PathVariable("id") Long id) {
            try{
                List<HttpRange> httpRangeList = HttpRange.parseRanges(httpRangeHeader);

                StreamBytesInfo streamBytesInfo = videoService
                        .getStreamBytes(id, httpRangeList.size() > 0 ? httpRangeList.get(0) : null)
                        .orElseThrow(NotFoundException::new);

                long byteLength = streamBytesInfo.getRangeEnd() - streamBytesInfo.getRangeStart() + 1;

                ResponseEntity.BodyBuilder builder = ResponseEntity
                        .status(httpRangeList.size() > 0 ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
                        .header("Content-Type", streamBytesInfo.getContentType())
                        .header("Accept-Ranges", "bytes")
                        .header("Content-Length", Long.toString(byteLength));

                if (httpRangeList.size() > 0) {
                    builder.header(
                            "Content-Range",
                            "bytes " + streamBytesInfo.getRangeStart() +
                                    "-" + streamBytesInfo.getRangeEnd() +
                                    "/" + streamBytesInfo.getFileSize());
                }
                
                System.out.println("Error here 2");
                return builder.body(streamBytesInfo.getResponseBody());
            }catch(Exception ex){
                System.out.println("Error here 1");
            }
            return null;
    }



}