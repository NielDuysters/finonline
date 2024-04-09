package finonline.be.domain.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finonline.be.domain.model.CashflowCategory;
import finonline.be.domain.model.User;
import finonline.be.domain.services.interfaces.CashflowCategoryService;
import finonline.be.persistence.repositories.CashflowCategoryRepository;
import finonline.be.persistence.repositories.UserRepository;
import finonline.be.web.request.AddCashflowCategory;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collection;

@Service
public class CashflowCategoryServiceImpl implements CashflowCategoryService {

	@Autowired
	private CashflowCategoryRepository cashflowCategoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public CashflowCategory addCashflowCategory(CashflowCategory cashflowCategory) {
		try {
			return cashflowCategoryRepository.save(cashflowCategory);
		} catch (DataIntegrityViolationException e) {
			throw e;
		}
	}
	
	public CashflowCategory createCashflowCategory(AddCashflowCategory addCashflowCategory, Integer userId) {
		String labelStr = addCashflowCategory.getLabel();
		
		if (labelStr.isEmpty()) {
			throw new IllegalArgumentException("Label of category cannot be empty.");
		}
		
		String labelStrCapitalized = labelStr.substring(0, 1).toUpperCase() + labelStr.substring(1).toLowerCase();
		
		CashflowCategory cashflowCategory = new CashflowCategory();
		cashflowCategory.generateRandomColor();
		cashflowCategory.setLabel(labelStrCapitalized);
		cashflowCategory.setType(addCashflowCategory.getType());
		cashflowCategory.setPersistent(false);
		
		User user = userRepository.getReferenceById(userId);
		cashflowCategory.setUser(user);
		
		try {
			return cashflowCategoryRepository.save(cashflowCategory);
		} catch (DataIntegrityViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection<CashflowCategory> getCashflowCategoriesByUserId(Integer userId) {
		return cashflowCategoryRepository.findByUserId(userId);
	}
	
	public void deleteCashflowCategoryById(Integer cashflowCategoryId) throws Exception {
		CashflowCategory cashflowCategory = cashflowCategoryRepository.getReferenceById(cashflowCategoryId);
		
		if (cashflowCategory.isPersistent()) {
			throw new Exception("This CashflowCategory can't be deleted.");
		}
		
		cashflowCategoryRepository.deleteById(cashflowCategoryId);
	}
}
