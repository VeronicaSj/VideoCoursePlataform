package videocurseapp.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import videocurseapp.demo.Endpoints;


@RestController
@RequestMapping(Endpoints.EP_USER_ROOT)
public class UserController {
    /*@Autowired
    private UserRepository userRepo;

    @GetMapping(Endpoints.EP_USER_GET_ID)
    public ResponseEntity<Model> getById(@PathVariable String name, Model model, Principal principal) {
        ResponseEntity<Model> res = ResponseEntity.notFound().build();
        Optional<User> opt = userRepo.findById(name);
        if(opt!=null  && opt.isPresent()){
            User foundUser = opt.get();
            if (foundUser != null) {
                model.addAttribute("user", foundUser);
                res= ResponseEntity.ok(model);
            }
        }
        return res;
        
    }

    @GetMapping(Endpoints.EP_USER_GET_ALL)
    public ResponseEntity<Model> getAllUsers(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return ResponseEntity.ok(model);
    }

    /*curl -X POST http://localhost:8081/user -H "Content-Type: application/json" -d "{\"name\":\"John Doe\", \"mail\":\"john.doe@example.com\"}" */
    /*@PostMapping(path=Endpoints.EP_USER_POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucb) {
        User savedUser = userRepo.save(user);
        URI locationOfNewUser = ucb
                .path(Endpoints.EP_USER_GET_ID)
                .buildAndExpand(savedUser.getName())
                .toUri();
        System.out.println(locationOfNewUser);
        return ResponseEntity.created(locationOfNewUser).build();
        return null;
    }

    /*curl -X PUT http://localhost:8081/user/1 -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"John Doe\",\"mail\":\"prueba2\",\"date\":null,\"img\":null,\"type\":null,\"lastLog\":null}" */
    /*@PutMapping(path=Endpoints.EP_USER_PUT)
    private ResponseEntity<Void> putUser(@PathVariable String name, @RequestBody User userUpdate) {
        userRepo.save(userUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path=Endpoints.EP_USER_DELETE)
    private ResponseEntity<Void> deleteUser(@PathVariable String name) {
        userRepo.delete(new User(name));
        return ResponseEntity.noContent().build();
    }

    */
}
