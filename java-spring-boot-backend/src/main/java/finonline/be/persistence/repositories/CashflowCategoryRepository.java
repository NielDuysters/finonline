package finonline.be.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import finonline.be.domain.model.CashflowCategory;

import java.util.Collection;

public interface CashflowCategoryRepository extends JpaRepository<CashflowCategory, Integer> {
	 public Collection<CashflowCategory> findByUserId(Integer userId);
}
