package finonline.be.mapper;

import finonline.be.domain.model.User;
import finonline.be.domain.types.CashflowType;
import finonline.be.io.swagger.model.CashflowCategory;
import finonline.be.io.swagger.model.CashflowcategoriesBody;
import finonline.be.io.swagger.model.CashflowcategoriesBody.CashflowTypeEnum;
import finonline.be.web.request.AddCashflowCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T09:00:50+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CashflowCategoryMapperImpl implements CashflowCategoryMapper {

    @Override
    public AddCashflowCategory toAddCashflowCategory(CashflowcategoriesBody body) {
        if ( body == null ) {
            return null;
        }

        AddCashflowCategory addCashflowCategory = new AddCashflowCategory();

        addCashflowCategory.setLabel( body.getLabel() );
        addCashflowCategory.setType( cashflowTypeEnumToCashflowType( body.getType() ) );

        return addCashflowCategory;
    }

    @Override
    public CashflowCategory toCashflowCategoryResponse(finonline.be.domain.model.CashflowCategory cashflowCategory) {
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

    protected CashflowType cashflowTypeEnumToCashflowType(CashflowTypeEnum cashflowTypeEnum) {
        if ( cashflowTypeEnum == null ) {
            return null;
        }

        CashflowType cashflowType;

        switch ( cashflowTypeEnum ) {
            case REVENUE: cashflowType = CashflowType.REVENUE;
            break;
            case EXPENSE: cashflowType = CashflowType.EXPENSE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + cashflowTypeEnum );
        }

        return cashflowType;
    }

    protected finonline.be.io.swagger.model.CashflowCategory.CashflowTypeEnum cashflowTypeToCashflowTypeEnum(CashflowType cashflowType) {
        if ( cashflowType == null ) {
            return null;
        }

        finonline.be.io.swagger.model.CashflowCategory.CashflowTypeEnum cashflowTypeEnum;

        switch ( cashflowType ) {
            case REVENUE: cashflowTypeEnum = finonline.be.io.swagger.model.CashflowCategory.CashflowTypeEnum.REVENUE;
            break;
            case EXPENSE: cashflowTypeEnum = finonline.be.io.swagger.model.CashflowCategory.CashflowTypeEnum.EXPENSE;
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
}
