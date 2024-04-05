package finonline.be.persistence.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import finonline.be.domain.charts.RevenueAndExpensesMonthly;
import finonline.be.domain.charts.TransactionsPerCategory;
import finonline.be.domain.model.Cashflow;
import finonline.be.domain.types.CashflowType;

import java.util.Collection;

public interface CashflowRepository extends JpaRepository<Cashflow, Integer> {
	 public Collection<Cashflow> findByUserId(Integer userId, Sort sort);
	 
	 @Query("SELECT NEW finonline.be.domain.charts.RevenueAndExpensesMonthly(SUM(amount) AS amount, to_char(transactionDate, 'FMMonth YYYY') AS dateYear, type) FROM Cashflow WHERE user.id = :userId GROUP BY type, to_char(transactionDate, 'FMMonth YYYY') ORDER BY MIN(transactionDate) ASC")
	 public Collection<RevenueAndExpensesMonthly> revenueAndExpensesMonthly(@Param("userId") Integer userId);
	 
	 @Query("SELECT NEW finonline.be.domain.charts.TransactionsPerCategory(SUM(amount) AS amount, cashflowCategory, type) FROM Cashflow WHERE user.id = :userId AND type = :type GROUP BY cashflowCategory, type")
	 public Collection<TransactionsPerCategory> transactionsPerCategory(@Param("userId") Integer userId, @Param("type") CashflowType type);
	 
	 @Query("SELECT NEW finonline.be.domain.charts.TransactionsPerCategory(SUM(amount) AS amount, cashflowCategory, type) FROM Cashflow WHERE user.id = :userId AND type = :type AND to_char(transactionDate, 'FMMonth YYYY') = :dateYear GROUP BY cashflowCategory, type")
	 public Collection<TransactionsPerCategory> transactionsPerCategoryMonthly(@Param("userId") Integer userId, @Param("type") CashflowType type, @Param("dateYear") String dateYear);
}
