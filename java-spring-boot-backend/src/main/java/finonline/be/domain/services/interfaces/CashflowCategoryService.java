package finonline.be.domain.services.interfaces;

import java.util.Collection;
import finonline.be.domain.model.CashflowCategory;
import finonline.be.web.request.AddCashflowCategory;

public interface CashflowCategoryService {
	
	public CashflowCategory addCashflowCategory(CashflowCategory cashflowCategory);
	public CashflowCategory createCashflowCategory(AddCashflowCategory addCashflowCategory, Integer userId);
	public Collection<CashflowCategory> getCashflowCategoriesByUserId(Integer userId);
	public void deleteCashflowCategoryById(Integer cashflowCategoryId) throws Exception;
	
}
