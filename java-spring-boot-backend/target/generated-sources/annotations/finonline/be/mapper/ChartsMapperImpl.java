package finonline.be.mapper;

import finonline.be.domain.charts.RevenueAndExpensesMonthly;
import finonline.be.domain.charts.TransactionsPerCategory;
import finonline.be.domain.model.User;
import finonline.be.domain.types.CashflowType;
import finonline.be.io.swagger.model.CashflowCategory;
import finonline.be.io.swagger.model.CashflowCategory.CashflowTypeEnum;
import finonline.be.io.swagger.model.ChartRevenueAndExpensesMonthly;
import finonline.be.io.swagger.model.ChartRevenueAndExpensesMonthly.TypeEnum;
import finonline.be.io.swagger.model.ChartTransactionsPerCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T09:00:50+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ChartsMapperImpl implements ChartsMapper {

    @Override
    public ChartRevenueAndExpensesMonthly toChartRevenueAndExpensesMonthlyResponse(RevenueAndExpensesMonthly data) {
        if ( data == null ) {
            return null;
        }

        ChartRevenueAndExpensesMonthly chartRevenueAndExpensesMonthly = new ChartRevenueAndExpensesMonthly();

        chartRevenueAndExpensesMonthly.setAmount( data.getAmount() );
        chartRevenueAndExpensesMonthly.setDateYear( data.getDateYear() );
        chartRevenueAndExpensesMonthly.setType( cashflowTypeToTypeEnum( data.getType() ) );

        return chartRevenueAndExpensesMonthly;
    }

    @Override
    public ChartTransactionsPerCategory toChartTransactionsPerCategoryResponse(TransactionsPerCategory data) {
        if ( data == null ) {
            return null;
        }

        ChartTransactionsPerCategory chartTransactionsPerCategory = new ChartTransactionsPerCategory();

        chartTransactionsPerCategory.setAmount( data.getAmount() );
        chartTransactionsPerCategory.setCashflowCategory( cashflowCategoryToCashflowCategory( data.getCashflowCategory() ) );
        chartTransactionsPerCategory.setType( cashflowTypeToTypeEnum1( data.getType() ) );

        return chartTransactionsPerCategory;
    }

    protected TypeEnum cashflowTypeToTypeEnum(CashflowType cashflowType) {
        if ( cashflowType == null ) {
            return null;
        }

        TypeEnum typeEnum;

        switch ( cashflowType ) {
            case REVENUE: typeEnum = TypeEnum.REVENUE;
            break;
            case EXPENSE: typeEnum = TypeEnum.EXPENSE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + cashflowType );
        }

        return typeEnum;
    }

    protected CashflowTypeEnum cashflowTypeToCashflowTypeEnum(CashflowType cashflowType) {
        if ( cashflowType == null ) {
            return null;
        }

        CashflowTypeEnum cashflowTypeEnum;

        switch ( cashflowType ) {
            case REVENUE: cashflowTypeEnum = CashflowTypeEnum.REVENUE;
            break;
            case EXPENSE: cashflowTypeEnum = CashflowTypeEnum.EXPENSE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + cashflowType );
        }

        return cashflowTypeEnum;
    }

    protected finonline.be.io.swagger.model.User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        finonline.be.io.swagger.model.User user1 = new finonline.be.io.swagger.model.User();

        if ( user.getId() != null ) {
            user1.setId( user.getId().longValue() );
        }
        user1.setName( user.getName() );
        user1.setStartCapital( user.getStartCapital() );
        user1.setCurrentCapital( user.getCurrentCapital() );

        return user1;
    }

    protected CashflowCategory cashflowCategoryToCashflowCategory(finonline.be.domain.model.CashflowCategory cashflowCategory) {
        if ( cashflowCategory == null ) {
            return null;
        }

        CashflowCategory cashflowCategory1 = new CashflowCategory();

        cashflowCategory1.setId( cashflowCategory.getId() );
        cashflowCategory1.setLabel( cashflowCategory.getLabel() );
        cashflowCategory1.setColor( cashflowCategory.getColor() );
        cashflowCategory1.type( cashflowTypeToCashflowTypeEnum( cashflowCategory.getType() ) );
        cashflowCategory1.setPersistent( cashflowCategory.isPersistent() );
        cashflowCategory1.setUser( userToUser( cashflowCategory.getUser() ) );

        return cashflowCategory1;
    }

    protected finonline.be.io.swagger.model.ChartTransactionsPerCategory.TypeEnum cashflowTypeToTypeEnum1(CashflowType cashflowType) {
        if ( cashflowType == null ) {
            return null;
        }

        finonline.be.io.swagger.model.ChartTransactionsPerCategory.TypeEnum typeEnum;

        switch ( cashflowType ) {
            case REVENUE: typeEnum = finonline.be.io.swagger.model.ChartTransactionsPerCategory.TypeEnum.REVENUE;
            break;
            case EXPENSE: typeEnum = finonline.be.io.swagger.model.ChartTransactionsPerCategory.TypeEnum.EXPENSE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + cashflowType );
        }

        return typeEnum;
    }
}
