package finonline.be.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import finonline.be.domain.model.Cashflow;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CashflowMapper {
	
	CashflowMapper INSTANCE = Mappers.getMapper(CashflowMapper.class);

	@Mapping(source = "cashflow.id", target = "id")
	@Mapping(source = "cashflow.type", target = "type")
	@Mapping(source = "cashflow.amount", target = "amount")
	@Mapping(source = "cashflow.cashflowCategory", target = "cashflowCategory")
	@Mapping(source = "cashflow.description", target = "description")
	@Mapping(source = "cashflow.transactionDate", target = "transactionDate")
	@Mapping(source = "cashflow.user", target = "user")
	finonline.be.io.swagger.model.Cashflow toCashflowResponse(Cashflow cashflow);
	
	default List<finonline.be.io.swagger.model.Cashflow> cashflowListToCashflowResponse(List<Cashflow> cashflowList) {
		return cashflowList.stream()
                .map(this::toCashflowResponse)
                .collect(Collectors.toList());
	}
}
