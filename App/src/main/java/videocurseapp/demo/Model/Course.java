package videocurseapp.demo.Model;

import java.net.URI;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name="Course")
public class Course {

    public enum Coin {
        EUR, USD;
    }

    @Id
    @Column(name="id")
    Integer id;

    @Column(name="name")
    String name;

    @Column(name="description")
    String description;

    @Column(name="price")
    Integer price;

    @Column(name="coin")
    Coin coin;

    @Column(name="punctuation")
    Boolean punctuation;
    
    @Column(name="creation_date")
    Date creationDate;

    @Column(name="last_update")
    Date lastUpdate;

    @Column(name="deletion_date")
    Date deletionDate;

    @Column(name="is_public")
    Boolean isPublic;

    @Column(name="is_holded")
    Boolean isHolded;

    @Column(name="img")
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    Image img;

    //relationship atributes

    Profesor profesor;
    List<Category> categoryList;
    List<User> alumnList;
}
