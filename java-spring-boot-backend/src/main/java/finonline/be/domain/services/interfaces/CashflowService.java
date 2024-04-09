package finonline.be.domain.services.interfaces;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import finonline.be.domain.charts.RevenueAndExpensesMonthly;
import finonline.be.domain.charts.TransactionsPerCategory;
import finonline.be.domain.model.Cashflow;
import finonline.be.domain.request.AddCashflow;
import finonline.be.domain.types.CashflowType;

public interface CashflowService {
	
	public Cashflow createCashflow(AddCashflow addCashflow, Integer userId);
	public Collection<Cashflow> getCashflowByUserId(Integer userId);
	public void deleteCashflowById(Integer cashflowId) throws Exception;
	public Collection<RevenueAndExpensesMonthly> getCashflowRevenueAndExpensesMonthly(Integer userId);
	public Collection<TransactionsPerCategory> getCashflowTransactionsPerCategory(Integer userId, CashflowType type);
	public Collection<TransactionsPerCategory> getCashflowTransactionsPerCategoryMonthly(Integer userId, CashflowType type, String dateYear);
	public Map<String, BigDecimal> getCapitalEvolutionOfUser(Integer userId);
	public List<String> getPeriodsByUserId(Integer userId);
	
}
