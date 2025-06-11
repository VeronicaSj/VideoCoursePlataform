package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Service.CourseService;
import videocurseapp.demo.Service.PaypalService;
import videocurseapp.demo.Service.ReceiptService;
import videocurseapp.demo.Service.UserService;

@Controller
public class PaypalController {

	@Autowired
	PaypalService service;
	@Autowired
	CourseService courseService;
	@Autowired
	ReceiptService receiptService;
	@Autowired
	UserService userService;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	private Course tempcourse =null;

	@PostMapping("/pay/{id}")
	public String payment(Model model,
		@PathVariable long id) {
		try {
			tempcourse = courseService.find(id);
            if (tempcourse!=null) {
                System.out.println(tempcourse.getCoin().name());
				Payment payment = service.createPayment(new Double(tempcourse.getPrice()), tempcourse.getCoin().name() , "PAYPAL",
					"sale", tempcourse.getName(), "http://localhost:8082/" + CANCEL_URL,
					"http://localhost:8082/" + SUCCESS_URL);
				for (Links link : payment.getLinks()) {
					if (link.getRel().equals("approval_url")) {
						return "redirect:" + link.getHref();
					}
				}
            }else{
				return "redirect:/"+CANCEL_URL;
			}
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(value = CANCEL_URL)
	public String cancelPay(Model model) {
		model.addAttribute("msg", "The pay could not be done");
		return "confirmation_msg";
	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(Model model, 
		@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = service.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				receiptService.save(userService.findInUseUser(), tempcourse);
				model.addAttribute("msg", "You have made the pay");
				return "confirmation_msg";
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}

}