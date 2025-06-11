package videocurseapp.demo.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    @OneToOne
    private Course course;

    @OneToOne
    private User alumn;

    private float price;

    private boolean isAlreadyPaid;

    public Receipt(User alumn, Course course) {
        this.alumn = alumn;
        this.course = course;
        this.date = new Date();
        this.price = course.getPrice();
        this.isAlreadyPaid=false;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getAlumn() {
        return alumn;
    }

    public void setAlumn(User alumn) {
        this.alumn = alumn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Receipt() {
    }

    public Receipt(long id) {
        this.id = id;
    }

    

    
}
