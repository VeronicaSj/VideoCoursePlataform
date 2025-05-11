package videocurseapp.demo.Utilities;

public class NavLink {
    private String herf;
    private String text;

    public NavLink(String herf, String text){
        this.herf=herf;
        this.text=text;
    }

    public String getHerf() {
        return herf;
    }

    public void setHerf(String herf) {
        this.herf = herf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
