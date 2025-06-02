package videocurseapp.demo.Model;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;


/**
 * Contains the values of extracted video content and metadata
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Video{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] content;

    private String title;

    public Video(String title) {
        this.title=title;
    }

    public Video(long id) {
        this.id = id;
    }

    public Video() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoBase64() {
        return Base64.getEncoder().encodeToString(this.content);
    }   
}