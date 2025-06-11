package videocurseapp.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Model.Receipt;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Repository.ReceiptRepository;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    public Receipt save(User alumn, Course course) {
    Receipt res = null;
    if(alumn!=null && course!=null){
        res = receiptRepository.save(new Receipt(alumn, course));
    }
    return res;
  }
}
