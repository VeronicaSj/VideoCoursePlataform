package videocurseapp.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import videocurseapp.demo.Model.Receipt;

@Repository()
public interface ReceiptRepository extends CrudRepository<Receipt, Long>{

}
