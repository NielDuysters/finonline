package finonline.be.web.controllers;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import finonline.be.domain.types.CashflowType;
import finonline.be.io.swagger.api.CashflowApi;
import finonline.be.io.swagger.model.AuthResponse;
import finonline.be.io.swagger.model.CashflowcategoriesBody;
import finonline.be.mapper.AuthResponseMapper;
import finonline.be.mapper.CashflowCategoryMapper;
import finonline.be.mapper.CashflowMapper;
import finonline.be.web.request.AddCashflow;
import finonline.be.web.request.AddCashflowCategory;
import finonline.be.web.request.UserAuthRequest;
import io.jsonwebtoken.Claims;
import finonline.be.io.swagger.model.CashflowBody;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cashflow")
public class CashflowController implements CashflowApi {
	
	@Autowired
	private CashflowServiceImpl cashflowService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	@Override
	public ResponseEntity<finonline.be.io.swagger.model.Cashflow> addCashflow(Authentication authentication, HttpServletRequest request, @Valid Object body) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			
			Map<String, Object> bodyMap = (Map<String, Object>) body;
			AddCashflow addCashflow = new AddCashflow(
				CashflowType.valueOf((String) bodyMap.get("type")),
				(Integer) bodyMap.get("categoryId"),
				BigDecimal.valueOf((Integer) bodyMap.get("amount")),
				(String) bodyMap.get("description"),
				(String) bodyMap.get("transactionDate")
			);
			
			Cashflow createdCashflow = cashflowService.createCashflow(addCashflow, userId);
			
			finonline.be.io.swagger.model.Cashflow response = CashflowMapper.INSTANCE.toCashflowResponse(createdCashflow);
			
			return new ResponseEntity<finonline.be.io.swagger.model.Cashflow>(response, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}  catch (Exception e) {
			
			System.out.println(e);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	@Override
	public ResponseEntity<List<finonline.be.io.swagger.model.Cashflow>> getCashflow(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
	
		try {
			Collection<Cashflow> cashflow = cashflowService.getCashflowByUserId(userId);
			
			List<finonline.be.io.swagger.model.Cashflow> response = CashflowMapper.INSTANCE.cashflowListToCashflowResponse((List<Cashflow>) cashflow);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> deleteCashflow(Authentication authentication, HttpServletRequest request, @PathVariable Integer id) {
		try {
			cashflowService.deleteCashflowById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}
}
	
