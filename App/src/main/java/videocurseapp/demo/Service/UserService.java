package videocurseapp.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieves user details by username from the database
        return (User) userRepo.findByUsername(username);
    }

    public String create(String username, String password, String email) {
        // Encodes the password and creates a new User object
        User user = new User(username, new BCryptPasswordEncoder().encode(password), email);
        // Saves the new user to the database
        userRepo.save(user);
        
        return "Create Successfully !"; // Returns a success message
    }

    public boolean delete(User user) {
        boolean res = true;
        try {
            userRepo.delete(user);

            if(!userRepo.findById(user.getUsername()).equals(Optional.empty())){
                res = false;
            }
        }catch(Exception e){
            res = false;
        }
        return res;
    }

    public boolean changePw(User user){
        boolean res = false;
        System.out.println(user.toString());
        User suser = userRepo.save(user);
        System.out.println(suser.toString());
        if(suser.equals(user)){
            res=true;
        }
        return res;
    }

    public boolean update(User user){
        boolean res = false;
        System.out.println(user.toString());
        User suser = userRepo.save(user);
        System.out.println(suser.toString());
        if(suser.equals(user)){
            res=true;
        }
        return res;
    }

    public User findInUseUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = (User) userRepo.findByUsername(user.getUsername());
        System.out.println(user);
        return user;
    }
}
