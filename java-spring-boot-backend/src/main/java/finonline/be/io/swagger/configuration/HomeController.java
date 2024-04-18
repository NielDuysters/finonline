package finonline.be.io.swagger.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
@RequestMapping("/apishit")
public class HomeController {
	
	@GetMapping
    public ResponseEntity<?> index() {
    	System.out.println("rofl");
    	return ResponseEntity.ok("rofl");
       // return "redirect:/swagger-ui/";
    }
}
