package finonline.be.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import finonline.be.domain.model.CashflowCategory;
import finonline.be.io.swagger.model.CashflowcategoriesBody;
import finonline.be.web.request.AddCashflowCategory;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CashflowCategoryMapper {
	
	CashflowCategoryMapper INSTANCE = Mappers.getMapper(CashflowCategoryMapper.class);

	@Mapping(source = "body.label", target = "label")
	@Mapping(source = "body.type", target = "type")
    AddCashflowCategory toAddCashflowCategory(CashflowcategoriesBody body);
	
	@Mapping(source = "cashflowCategory.id", target = "id")
	@Mapping(source = "cashflowCategory.label", target = "label")
	@Mapping(source = "cashflowCategory.color", target = "color")
	@Mapping(source = "cashflowCategory.type", target = "type")
	@Mapping(source = "cashflowCategory.persistent", target = "persistent")
	@Mapping(source = "cashflowCategory.user", target = "user")
	finonline.be.io.swagger.model.CashflowCategory toCashflowCategoryResponse (CashflowCategory cashflowCategory);
	
	default List<finonline.be.io.swagger.model.CashflowCategory> cashflowCategoryListToCashflowCategoryResponse(List<CashflowCategory> cashflowCategoryList) {
		return cashflowCategoryList.stream()
                .map(this::toCashflowCategoryResponse)
                .collect(Collectors.toList());
	}
}
