package videocurseapp.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {

	private String clientId="AS8OETNp9pc64LIhpU4unckrlHa64vYCvZhUDsJVd-QRPZo3b2uV_JIhSpeYN7XvLDMkGvRPlxVBQPpx";
	private String clientSecret="EMwM6z-aeNzEJb7XBfL-qKzZIlAa1nKj6LGQWG6b1QiHhy7iTfFnJW98MLmAxDXI9_uMJ17X0HD0bhG8";
	private String mode="sandbox";

	@Bean
	public Map<String, String> paypalSdkConfig() {
		Map<String, String> configMap = new HashMap<>();
		configMap.put("mode", mode);
		return configMap;
	}

	@Bean
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}

	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkConfig());
		return context;
	}

}