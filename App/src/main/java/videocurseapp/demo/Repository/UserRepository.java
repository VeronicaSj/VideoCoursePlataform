package videocurseapp.demo.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import videocurseapp.demo.Model.User;

@Repository()
public interface UserRepository extends CrudRepository<User, String>{

}
