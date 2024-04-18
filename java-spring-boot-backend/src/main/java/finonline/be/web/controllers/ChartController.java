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
import finonline.be.io.swagger.api.ChartsApi;
import finonline.be.io.swagger.model.ChartRevenueAndExpensesMonthly;
import finonline.be.io.swagger.model.ChartTransactionsPerCategory;
import finonline.be.mapper.CashflowMapper;
import finonline.be.mapper.ChartsMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/charts")
public class ChartController implements ChartsApi {
	
	@Autowired
	private CashflowServiceImpl cashflowService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	@GetMapping("/periods")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<String>> getChartPeriods(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		
		try {
			List<String> periods = cashflowService.getPeriodsByUserId(userId);
			return ResponseEntity.ok(periods);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	@GetMapping("/revenue-expenses-monthly")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<ChartRevenueAndExpensesMonthly>> getChartRevenueAndExpensesMonthly(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			Collection<RevenueAndExpensesMonthly> chartData = cashflowService.getCashflowRevenueAndExpensesMonthly(userId);
			
			List<ChartRevenueAndExpensesMonthly> response = ChartsMapper.INSTANCE.toListChartRevenueAndExpensesMonthlyResponse((List<RevenueAndExpensesMonthly>) chartData);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	@GetMapping("/transactions-per-category/{type}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<ChartTransactionsPerCategory>> getChartTransactionsPerCategory(Authentication authentication, HttpServletRequest request, String type) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			Collection<TransactionsPerCategory> chartData = cashflowService.getCashflowTransactionsPerCategory(userId, CashflowType.valueOf(type));
			List<ChartTransactionsPerCategory> response = ChartsMapper.INSTANCE.toListChartTransactionsPerCategoryResponse((List<TransactionsPerCategory>) chartData);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	@GetMapping("/transactions-per-category/{type}/{period}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<ChartTransactionsPerCategory>> getChartTransactionsPerCategoryPerPeriod(Authentication authentication, HttpServletRequest request, String type,
			String period) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			Collection<TransactionsPerCategory> chartData = cashflowService.getCashflowTransactionsPerCategoryMonthly(userId, CashflowType.valueOf(type), period);
			List<ChartTransactionsPerCategory> response = ChartsMapper.INSTANCE.toListChartTransactionsPerCategoryResponse((List<TransactionsPerCategory>) chartData);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	@GetMapping("/user-capital-evolution")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Map<String, BigDecimal>> getChartUserCapitalEvolution(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		try {
			Map<String, BigDecimal> chartData = cashflowService.getCapitalEvolutionOfUser(userId);
			return ResponseEntity.ok(chartData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
	
