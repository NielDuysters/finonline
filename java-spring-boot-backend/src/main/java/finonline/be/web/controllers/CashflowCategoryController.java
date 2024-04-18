package finonline.be.web.controllers;

import java.util.Collection;
import java.util.List;

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
import finonline.be.domain.model.CashflowCategory;
import finonline.be.domain.services.implementations.CashflowCategoryServiceImpl;
import finonline.be.io.swagger.api.CashflowcategoriesApi;
import finonline.be.io.swagger.model.AuthResponse;
import finonline.be.io.swagger.model.CashflowcategoriesBody;
import finonline.be.mapper.AuthResponseMapper;
import finonline.be.mapper.CashflowCategoryMapper;
import finonline.be.web.request.AddCashflowCategory;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cashflowcategories")
public class CashflowCategoryController implements CashflowcategoriesApi {
	
	@Autowired
	private CashflowCategoryServiceImpl cashflowCategoryService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<finonline.be.io.swagger.model.CashflowCategory>> getCashflowCategories(Authentication authentication, HttpServletRequest request) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		List<CashflowCategory> cashflowCategories = (List<CashflowCategory>) cashflowCategoryService.getCashflowCategoriesByUserId(userId);
		
		List<finonline.be.io.swagger.model.CashflowCategory> response = CashflowCategoryMapper.INSTANCE.cashflowCategoryListToCashflowCategoryResponse(cashflowCategories);
		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> deleteCashflowCategory(Integer id) {
		try {
			cashflowCategoryService.deleteCashflowCategoryById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
 

	@Override
	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ResponseEntity<finonline.be.io.swagger.model.CashflowCategory> addCashflowCategory(Authentication authentication, HttpServletRequest request, @Valid CashflowcategoriesBody body) {
		Claims claims = jwtUtil.resolveClaims(request);
		Integer userId = (Integer) claims.get("id");
		
		AddCashflowCategory addCashflowCategory = CashflowCategoryMapper.INSTANCE.toAddCashflowCategory(body);
		
		try {
			CashflowCategory createdCashflowCategory = cashflowCategoryService.createCashflowCategory(addCashflowCategory, userId);
			
			finonline.be.io.swagger.model.CashflowCategory response = CashflowCategoryMapper.INSTANCE.toCashflowCategoryResponse(createdCashflowCategory);
			return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<finonline.be.io.swagger.model.CashflowCategory>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<finonline.be.io.swagger.model.CashflowCategory>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
	
