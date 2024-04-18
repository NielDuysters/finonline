package finonline.be.web.controllers;

import java.util.Map;

import javax.validation.Valid;

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
import finonline.be.domain.services.implementations.UserServiceImpl;
import finonline.be.io.swagger.api.AuthApi;
import finonline.be.io.swagger.model.AuthResponse;
import finonline.be.io.swagger.model.InlineResponse200;
import finonline.be.mapper.AuthResponseMapper;
import finonline.be.web.request.UserAuthRequest;
import finonline.be.web.response.UserAuthResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController implements AuthApi {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@ResponseBody
	@PostMapping
	public  ResponseEntity<AuthResponse> auth(@Valid Object body) {
		try {
			
			
			Map<String, String> bodyMap = (Map<String, String>) body;
		    UserAuthRequest userAuthReq = new UserAuthRequest(bodyMap.get("name"), bodyMap.get("pass"));
			
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthReq.getName(), userAuthReq.getPass()));
            
            String name = authentication.getName();    
            User user = userService.getUserByName(name);
            
            String token = jwtUtil.createToken(user);
            UserAuthResponse authRes = new UserAuthResponse(user.getId(), name, token);
            AuthResponse response = AuthResponseMapper.INSTANCE.toAuthResponse(authRes);
            
            return ResponseEntity.ok(response);
         

        } catch (BadCredentialsException e) {
            return new ResponseEntity<AuthResponse>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<AuthResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<InlineResponse200> checkAuth(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		
		InlineResponse200 response = new InlineResponse200();
		response.setId((int) claims.get("id"));
		response.setName(claims.getSubject());
		
		 return ResponseEntity.ok(response);
	}
	
}
	
