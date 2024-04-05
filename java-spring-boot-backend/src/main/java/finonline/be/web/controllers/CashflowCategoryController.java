package finonline.be.web.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import finonline.be.auth.JwtUtil;
import finonline.be.domain.model.CashflowCategory;
import finonline.be.domain.request.AddCashflowCategory;
import finonline.be.domain.services.implementations.CashflowCategoryServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cashflowcategories")
public class CashflowCategoryController {
	
	@Autowired
	private CashflowCategoryServiceImpl cashflowCategoryService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getCashflowCategoriesByUserId(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		Collection<CashflowCategory> cashflowCategories = cashflowCategoryService.getCashflowCategoriesByUserId(userId);
		
		return ResponseEntity.ok(cashflowCategories);
	}
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> addCashflowCategory(Authentication authentication, HttpServletRequest request, @RequestBody AddCashflowCategory addCashflowCategory) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			CashflowCategory createdCashflowCategory = cashflowCategoryService.createCashflowCategory(addCashflowCategory, userId);
			return new ResponseEntity<CashflowCategory>(createdCashflowCategory, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteCashflowCategory(Authentication authentication, HttpServletRequest request, @PathVariable Integer id) {
		try {
			cashflowCategoryService.deleteCashflowCategoryById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
}
	
