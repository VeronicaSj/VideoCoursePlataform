package videocurseapp.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo; // Injects the UserRepo for accessing user data

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieves user details by username from the database
        return userRepo.findByUsername(username);
    }

    public String create(String username, String password) {
        // Encodes the password and creates a new User object
        User user = new User(username, new BCryptPasswordEncoder().encode(password), "student");
                
        
        // Saves the new user to the database
        userRepo.save(user);
        
        return "Create Successfully !"; // Returns a success message
    }

}
