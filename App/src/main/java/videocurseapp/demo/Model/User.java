package videocurseapp.demo.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User implements UserDetails {
    // Delimiter used to split authorities string
    private static final String AUTHORITIES_DELIMITER = "::";

    // Unique identifier for the user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Username of the user
    private String username;

    // Password of the user
    private String password;

    // Authorities granted to the user, stored as a single string
    private String authorities;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User() {
    }

    public User(String username, String password, String authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(int id) {
        this.id = id;
    }

    
    
    
}
