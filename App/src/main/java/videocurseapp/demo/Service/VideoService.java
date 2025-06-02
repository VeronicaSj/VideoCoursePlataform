package videocurseapp.demo.Service;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import videocurseapp.demo.Model.Video;
import videocurseapp.demo.Repository.VideoRepository;
import videocurseapp.demo.Utilities.StreamBytesInfo;


/**
 * Video Service that process the incoming request and extract the data out.
 */
@Service
public class VideoService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private VideoRepository videoRepo;

    Path root = Path.of( "src\\..\\..\\videos");

    public void saveNewVideo(String title, MultipartFile videofile) {
        Video video = videoRepo.save(new Video(title));
        System.out.println(video.getId());
        try {
            Path file = Path.of(root.toString(), video.getId()+".mp4");
            try (OutputStream output = Files.newOutputStream(file, CREATE, WRITE)) {
                videofile.getInputStream().transferTo(output);
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public Optional<StreamBytesInfo> getStreamBytes(Long id, HttpRange range) {
        Optional<Video> vmdById = videoRepo.findById(id);
        if (vmdById.isEmpty()) {
            System.out.println("Error here 1");
            return Optional.empty();
        }

        Path filePath = Path.of(root.toString(), String.valueOf(id)+ ".mp4");
        if (!Files.exists(filePath)) {
            
            System.out.println("2");
            return Optional.empty();
        }

        try {
            long fileSize = Files.size(filePath);
            long chunkSize = fileSize / 100; // took 1/100 part of file size

            //If no range is specified
            if (range == null) {
                return Optional.of(new StreamBytesInfo(
                        out -> Files.newInputStream(filePath).transferTo(out),
                        fileSize,
                        0,
                        fileSize,
                        "video/mp4"));
            }

            long rangeStart = range.getRangeStart(0); // Will be 0 if not specified
            long rangeEnd = rangeStart + chunkSize; // range.getRangeEnd(fileSize);

            if (rangeEnd >= fileSize) {
                rangeEnd = fileSize - 1;
            }

            final long finalRangeEnd = rangeEnd;

            return Optional.of(new StreamBytesInfo(
                    out -> {
                        try (InputStream inputStream = Files.newInputStream(filePath)) {
                            inputStream.skip(rangeStart);
                            byte[] bytes = inputStream.readNBytes((int) ((finalRangeEnd - rangeStart) + 1));
                            out.write(bytes);
                        }
                    },
                    fileSize, rangeStart, rangeEnd, "video/mp4"));
        } catch (IOException ex) {
            
            System.out.println("3");
            return Optional.empty();
        }
    }
}