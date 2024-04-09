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
import finonline.be.domain.model.Cashflow;
import finonline.be.domain.services.implementations.CashflowServiceImpl;
import finonline.be.web.request.AddCashflow;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cashflow")
public class CashflowController {
	
	@Autowired
	private CashflowServiceImpl cashflowService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> addCashflow(Authentication authentication, HttpServletRequest request, @RequestBody AddCashflow addCashflow) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		
		try {
			Cashflow createdCashflow = cashflowService.createCashflow(addCashflow, userId);
			return new ResponseEntity<Cashflow>(createdCashflow, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}  catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getCashflow(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			Collection<Cashflow> cashflow = cashflowService.getCashflowByUserId(userId);
			return ResponseEntity.ok(cashflow);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deleteCashflow(Authentication authentication, HttpServletRequest request, @PathVariable Integer id) {
		try {
			cashflowService.deleteCashflowById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}

}
	
