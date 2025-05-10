package videocurseapp.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import videocurseapp.demo.Model.Image;


@Repository()
public interface ImageRepository extends CrudRepository<Image, Long> {
}