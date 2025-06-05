package videocurseapp.demo.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User implements UserDetails {
    // Delimiter used to split authorities string
    private static final String AUTHORITIES_DELIMITER = "::";
    private static final String AUTH_REGULAR = "REGULAR";
    private static final String AUTH_PROF = "::PROFE";

    // Unique identifier for the user
    @Id
    private String username;

    // Password of the user
    private String password;

    private String email;

    // Authorities granted to the user, stored as a single string
    private String authorities;

    private boolean isProfessor;

    @OneToOne
    private Image avatar;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> createdCourses;

    public List<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void setCreatedCourses(List<Course> createdCourses) {
        this.createdCourses = createdCourses;
    }

    public void addcreatedCourse(Course c) {
        if(this.createdCourses==null){
            createdCourses = new ArrayList<Course>();
        }
        this.createdCourses.add(c);
    }

    

    /**
     * Returns the authorities granted to the user.
     * @return a collection of GrantedAuthority objects
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Split the authorities string and convert to a list of SimpleGrantedAuthority objects
        return Arrays.stream(this.authorities.split(AUTHORITIES_DELIMITER))
                     .map(SimpleGrantedAuthority::new)
                     .collect(Collectors.toList());
    }

    /**
     * Returns the password used to authenticate the user.
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + ", authorities="
                + authorities + ", isProfessor=" + isProfessor + ", avatar=" + avatar + "]";
    }

    /**
     * Returns the username used to authenticate the user.
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired.
     * @return true if the account is non-expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * @return true if the account is non-locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials have expired.
     * @return true if the credentials are non-expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public static String getAuthoritiesDelimiter() {
        return AUTHORITIES_DELIMITER;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
        if(isProfessor){
            authorities += AUTH_PROF;
        }else{
            authorities.replace(AUTH_PROF, "");
        }
        System.out.println(authorities);

    }

    public User() {
        isProfessor=false;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = AUTH_REGULAR;
        isProfessor=false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String username) {
        this.username = username;
    }

    public Image getAvatar() {
        Image res = Image.DEFAULT_AVATAR;
        if(avatar!=null){
            res = avatar;
        }
        return res;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
