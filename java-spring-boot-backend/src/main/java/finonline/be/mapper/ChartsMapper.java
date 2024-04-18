package finonline.be.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import finonline.be.domain.charts.RevenueAndExpensesMonthly;
import finonline.be.domain.charts.TransactionsPerCategory;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChartsMapper {
	
	ChartsMapper INSTANCE = Mappers.getMapper(ChartsMapper.class);

	@Mapping(source = "data.amount", target = "amount")
	@Mapping(source = "data.dateYear", target = "dateYear")
	@Mapping(source = "data.type", target = "type")
	finonline.be.io.swagger.model.ChartRevenueAndExpensesMonthly toChartRevenueAndExpensesMonthlyResponse(RevenueAndExpensesMonthly data);
	
	default List<finonline.be.io.swagger.model.ChartRevenueAndExpensesMonthly> toListChartRevenueAndExpensesMonthlyResponse(List<RevenueAndExpensesMonthly> dataList) {
		return dataList.stream()
                .map(this::toChartRevenueAndExpensesMonthlyResponse)
                .collect(Collectors.toList());
	}
	
	@Mapping(source = "data.amount", target = "amount")
	@Mapping(source = "data.cashflowCategory", target = "cashflowCategory")
	@Mapping(source = "data.type", target = "type")
	finonline.be.io.swagger.model.ChartTransactionsPerCategory toChartTransactionsPerCategoryResponse(TransactionsPerCategory data);

	default List<finonline.be.io.swagger.model.ChartTransactionsPerCategory> toListChartTransactionsPerCategoryResponse(List<TransactionsPerCategory> dataList) {
		return dataList.stream()
                .map(this::toChartTransactionsPerCategoryResponse)
                .collect(Collectors.toList());
	}
}
