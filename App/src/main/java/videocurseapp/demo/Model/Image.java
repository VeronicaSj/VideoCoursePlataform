package videocurseapp.demo.Model;

import java.io.FileInputStream;
import java.util.Base64;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Image {
  public static final Image DEFAULT_AVATAR = setDefaultImg("src\\main\\resources\\static\\resources\\avatar.png");
  public static final Image DEFAULT_IMG = setDefaultImg("src\\main\\resources\\static\\resources\\logo.png");

  private static Image setDefaultImg(String src){
    Image defimg = null;
		FileInputStream fiis = null;
    java.io.File file = new java.io.File(src);
    try{
      fiis = new FileInputStream(file);
			byte [] media = fiis.readAllBytes();
			fiis.read(media);
      defimg = new Image("DEFAULT_AVATAR", media);
			
		} catch (Exception e) {
      
      System.out.println("\n\n"+e.getMessage()+"\n\n");
		} finally {
			try { if(fiis != null) fiis.close(); } catch (Exception e) {}
		}
    System.out.println("\n\n"+defimg+"\n\n");
    return defimg;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  
  @Lob
  private byte[] img;

  public Image(String name, byte[] img) {
    this.name = name;
    this.img = img;
  }

  

  public Image() {
  }



  public Image(long id) {
    this.id = id;
  }



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

  public byte[] getImg() {
    return img;
  }



  public void setImg(byte[] img) {
    this.img = img;
  }
 
     public String getImgBase64() {
        return Base64.getEncoder().encodeToString(this.img);
    }   

}