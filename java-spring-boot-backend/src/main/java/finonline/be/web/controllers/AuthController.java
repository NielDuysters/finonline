package finonline.be.web.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import finonline.be.auth.JwtUtil;
import finonline.be.domain.model.User;
import finonline.be.domain.model.response.UserAuthResponse;
import finonline.be.domain.request.UserAuthRequest;
import finonline.be.domain.services.implementations.UserServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserServiceImpl userService;
	
	@ResponseBody
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody UserAuthRequest userAuthReq) {
		try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthReq.getName(), userAuthReq.getPass()));
            
            String name = authentication.getName();    
            User user = userService.getUserByName(name);
            
            String token = jwtUtil.createToken(user);
            UserAuthResponse authRes = new UserAuthResponse(user.getId(), name, token);

            return ResponseEntity.ok(authRes);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT authentication failed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> AuthenticatedUser(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		
		JSONObject json = new JSONObject();
		json.put("id", claims.get("id"));
		json.put("name", claims.getSubject());
		
		return ResponseEntity.ok(json.toString());
	}
	
}
	
