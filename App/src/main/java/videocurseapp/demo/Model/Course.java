package videocurseapp.demo.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Course")
public class Course {

    public Course() {
    }

    public enum Coin {
        EUR, USD;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="name")
    String name;

    @Column(name="description")
    String description;

    @Column(name="price")
    float price;

    @Column(name="coin")
    Coin coin;

    @Column(name="punctuation")
    Float punctuation;
    
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

    @OneToOne
    private Image img;

    public Course(String name, String description, float price, Coin coin, Boolean isPublic) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.coin = coin;
        this.isPublic = isPublic;
    }

    public Course(long id) {
        this.id = id;
    }

    public Image getImg() {
        return img;
    }

    @ManyToMany
    List<Category> categoryList;

    @ManyToMany
    List<User> alumnList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Float getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(Float punctuation) {
        this.punctuation = punctuation;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getIsHolded() {
        return isHolded;
    }

    public void setIsHolded(Boolean isHolded) {
        this.isHolded = isHolded;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<User> getAlumnList() {
        return alumnList;
    }

    public void setAlumnList(List<User> alumnList) {
        this.alumnList = alumnList;
    }

    
}
