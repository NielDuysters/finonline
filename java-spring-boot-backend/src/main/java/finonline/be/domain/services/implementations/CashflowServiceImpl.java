package finonline.be.domain.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import finonline.be.domain.charts.RevenueAndExpensesMonthly;
import finonline.be.domain.charts.TransactionsPerCategory;
import finonline.be.domain.model.Cashflow;
import finonline.be.domain.model.CashflowCategory;
import finonline.be.domain.model.User;
import finonline.be.domain.request.AddCashflow;
import finonline.be.domain.services.interfaces.CashflowService;
import finonline.be.domain.types.CashflowType;
import finonline.be.persistence.repositories.CashflowCategoryRepository;
import finonline.be.persistence.repositories.CashflowRepository;
import finonline.be.persistence.repositories.UserRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CashflowServiceImpl implements CashflowService {

	@Autowired
	private CashflowRepository cashflowRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CashflowCategoryRepository cashflowCategoryRepository;
	
	public Cashflow createCashflow(AddCashflow addCashflow, Integer userId) {
		Cashflow cashflow = new Cashflow();
		cashflow.setType(addCashflow.getType());
		cashflow.setAmount(addCashflow.getAmount());
		cashflow.setDescription(addCashflow.getDescription());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(addCashflow.getTransactionDate(), formatter);
        Date transactionDate = Date.valueOf(localDate);
		cashflow.setTransactionDate(transactionDate);
		
		if (localDate.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Transaction date of Cashflow can't be after current date.");
		}
		
		User user = userRepository.getReferenceById(userId);
		cashflow.setUser(user);
		
		CashflowCategory cashflowCategory = cashflowCategoryRepository.getReferenceById(addCashflow.getCategoryId());
		cashflowCategory.getId();
		cashflow.setCashflowCategory(cashflowCategory);
		
		try {
			return cashflowRepository.save(cashflow);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection<Cashflow> getCashflowByUserId(Integer userId) {
		try {
			Sort sortByTransactionDate = Sort.by("transactionDate").descending();
			Collection<Cashflow> cashflow = cashflowRepository.findByUserId(userId, sortByTransactionDate);
			return cashflow;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteCashflowById(Integer cashflowId) throws Exception {
		cashflowRepository.deleteById(cashflowId);
	}
	
	public Collection<RevenueAndExpensesMonthly> getCashflowRevenueAndExpensesMonthly(Integer userId) {
		try {
			Collection<RevenueAndExpensesMonthly> cashflowData = cashflowRepository.revenueAndExpensesMonthly(userId);
			return cashflowData;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection<TransactionsPerCategory> getCashflowTransactionsPerCategory(Integer userId, CashflowType type) {
		try {
			Collection<TransactionsPerCategory> cashflowData = cashflowRepository.transactionsPerCategory(userId, type);
			return cashflowData;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Collection<TransactionsPerCategory> getCashflowTransactionsPerCategoryMonthly(Integer userId, CashflowType type, String dateYear) {
		try {
			Collection<TransactionsPerCategory> cashflowData = cashflowRepository.transactionsPerCategoryMonthly(userId, type, dateYear);
			return cashflowData;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Map<String, BigDecimal> getCapitalEvolutionOfUser(Integer userId) {
		try {
			Map<String, BigDecimal> data = new HashMap<String, BigDecimal>();
			List<String> periods = this.getPeriodsByUserId(userId);
			
			Collection<RevenueAndExpensesMonthly> cashflowDataMonthly = cashflowRepository.revenueAndExpensesMonthly(userId);
			
			User user = userRepository.getReferenceById(userId);
			BigDecimal amount = user.getStartCapital();
			
			for (Integer i = 0; i < periods.size(); i++) {
				String period = periods.get(i);
				Boolean putted = false;
				
				for (RevenueAndExpensesMonthly rem : cashflowDataMonthly) {
					if (rem.getDateYear().equals(period)) {
						
						
						if (rem.getType() == CashflowType.REVENUE) {
							amount = amount.add(rem.getAmount());
						} else {
							amount = amount.subtract(rem.getAmount());
						}
						
						data.put(period, amount);
						putted = true;
					}
				}
				
				if (!putted) {
					data.put(period, amount);
				}
			}
			
			
			return data;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<String> getPeriodsByUserId(Integer userId) {
		List<String> periods = new ArrayList<String>();
		
		Collection<RevenueAndExpensesMonthly> cashflowDataMonthly = cashflowRepository.revenueAndExpensesMonthly(userId);
		
		if (cashflowDataMonthly.size() == 0) {
			return periods;
		}
		
		DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder().parseDefaulting(ChronoField.DAY_OF_MONTH, 1).parseCaseInsensitive().appendPattern("MMMM yyyy").toFormatter(Locale.ENGLISH);
		LocalDate firstDate = LocalDate.parse(cashflowDataMonthly.stream().findFirst().get().getDateYear(), dateFormatter);
		LocalDate lastDate = cashflowDataMonthly.stream().reduce((first, second) -> second).map(d -> LocalDate.parse(d.getDateYear(), dateFormatter)).orElse(null);
		
		for (LocalDate date = firstDate; !date.isAfter(lastDate); date = date.plusMonths(1)) {
			String periodstring = dateFormatter.format(date);
			periods.add(periodstring);
		}
		
		return periods;
	}
	
}
