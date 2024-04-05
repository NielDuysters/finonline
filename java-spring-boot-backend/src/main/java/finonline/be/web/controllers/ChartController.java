package finonline.be.web.controllers;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import finonline.be.auth.JwtUtil;
import finonline.be.domain.charts.RevenueAndExpensesMonthly;
import finonline.be.domain.charts.TransactionsPerCategory;
import finonline.be.domain.services.implementations.CashflowServiceImpl;
import finonline.be.domain.types.CashflowType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/charts")
public class ChartController {
	
	@Autowired
	private CashflowServiceImpl cashflowService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/revenue-expenses-monthly")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getRevenueAndExpensesMonthly(HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			Collection<RevenueAndExpensesMonthly> chartData = cashflowService.getCashflowRevenueAndExpensesMonthly(userId);
			return ResponseEntity.ok(chartData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/transactions-per-category/{type}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getTransactionsPerCategory(Authentication authentication, HttpServletRequest request, @PathVariable CashflowType type) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			Collection<TransactionsPerCategory> chartData = cashflowService.getCashflowTransactionsPerCategory(userId, type);
			return ResponseEntity.ok(chartData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@GetMapping("/transactions-per-category/{type}/{dateYear}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getTransactionsPerCategoryMonthly(Authentication authentication, HttpServletRequest request, @PathVariable CashflowType type, @PathVariable String dateYear) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			Collection<TransactionsPerCategory> chartData = cashflowService.getCashflowTransactionsPerCategoryMonthly(userId, type, dateYear);
			return ResponseEntity.ok(chartData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@GetMapping("/user-capital-evolution")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getUserCapitalEvolution(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			Map<String, BigDecimal> chartData = cashflowService.getCapitalEvolutionOfUser(userId);
			return ResponseEntity.ok(chartData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@GetMapping("/periods")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getUserPeriods(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		
		try {
			List<String> periods = cashflowService.getPeriodsByUserId(userId);
			return ResponseEntity.ok(periods);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
}
	
