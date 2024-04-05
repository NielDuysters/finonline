package finonline.be.web.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finonline.be.auth.JwtUtil;
import finonline.be.domain.model.CashflowCategory;
import finonline.be.domain.model.User;
import finonline.be.domain.request.PatchUser;
import finonline.be.domain.services.implementations.CashflowCategoryServiceImpl;
import finonline.be.domain.services.implementations.UserServiceImpl;
import finonline.be.domain.types.CashflowType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private CashflowCategoryServiceImpl cashflowCategoryService;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		
		if (createdUser == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
		}
		
		// Default categories.
		CashflowCategory otherRevenue = new CashflowCategory("Other", "#DF01A5", CashflowType.REVENUE, true, createdUser);
		CashflowCategory otherExpense = new CashflowCategory("Other", "#DF01A5", CashflowType.EXPENSE, true, createdUser);
		
		// Other categories.
		CashflowCategory jobRevenue = new CashflowCategory("Job", "#0000FF", CashflowType.REVENUE, false, createdUser);
		CashflowCategory investmentRevenue = new CashflowCategory("Investment", "#FF0040", CashflowType.REVENUE, false, createdUser);
		CashflowCategory foodExpense = new CashflowCategory("Food", "#088A08", CashflowType.EXPENSE, false, createdUser);
		CashflowCategory electricityExpense = new CashflowCategory("Electricity", "#D7DF01", CashflowType.EXPENSE, false, createdUser);
		
		cashflowCategoryService.addCashflowCategory(otherRevenue);
		cashflowCategoryService.addCashflowCategory(otherExpense);
		
		cashflowCategoryService.addCashflowCategory(jobRevenue);
		cashflowCategoryService.addCashflowCategory(investmentRevenue);
		cashflowCategoryService.addCashflowCategory(foodExpense);
		cashflowCategoryService.addCashflowCategory(electricityExpense);
		
		
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getUser(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			User user = userService.getUserById(userId);
			
			if (user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User with id %d not found.", userId));
			}
			
			return ResponseEntity.ok(user);
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@PatchMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> updateUser(Authentication authentication, HttpServletRequest request, @RequestBody PatchUser patchUser) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			User patchedUser = userService.patchUser(userId, patchUser);
			return ResponseEntity.ok(patchedUser);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
}
