package videocurseapp.demo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * Contains the values of extracted video content and metadata
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Video{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    public Video(String title, int position) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}