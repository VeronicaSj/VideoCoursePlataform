package videocurseapp.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import videocurseapp.demo.Model.Image;


@Repository()
public interface ImageRepository extends CrudRepository<Image, Long> {
    @Query(value = "SELECT * FROM image WHERE name = ?1", nativeQuery = true)
    List<Image> findImageByName(String name);
}