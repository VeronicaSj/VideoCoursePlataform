package videocurseapp.demo.Utilities;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StreamBytesInfo {

    private final StreamingResponseBody responseBody;
    private final long fileSize;
    private final long rangeStart;
    private final long rangeEnd;
    private final String contentType;
}