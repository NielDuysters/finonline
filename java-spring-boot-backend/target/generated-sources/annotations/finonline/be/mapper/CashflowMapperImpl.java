package finonline.be.mapper;

import finonline.be.domain.model.User;
import finonline.be.domain.types.CashflowType;
import finonline.be.io.swagger.model.Cashflow;
import finonline.be.io.swagger.model.Cashflow.TypeEnum;
import finonline.be.io.swagger.model.CashflowCategory;
import finonline.be.io.swagger.model.CashflowCategory.CashflowTypeEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T09:00:50+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CashflowMapperImpl implements CashflowMapper {

    private final DatatypeFactory datatypeFactory;

    public CashflowMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public Cashflow toCashflowResponse(finonline.be.domain.model.Cashflow cashflow) {
        if ( cashflow == null ) {
            return null;
        }

        Cashflow cashflow1 = new Cashflow();

        cashflow1.setId( cashflow.getId() );
        cashflow1.setType( cashflowTypeToTypeEnum( cashflow.getType() ) );
        cashflow1.setAmount( cashflow.getAmount() );
        cashflow1.setCashflowCategory( cashflowCategoryToCashflowCategory( cashflow.getCashflowCategory() ) );
        cashflow1.setDescription( cashflow.getDescription() );
        cashflow1.setTransactionDate( xmlGregorianCalendarToString( dateToXmlGregorianCalendar( cashflow.getTransactionDate() ), null ) );
        cashflow1.setUser( userToUser( cashflow.getUser() ) );

        return cashflow1;
    }

    private String xmlGregorianCalendarToString( XMLGregorianCalendar xcal, String dateFormat ) {
        if ( xcal == null ) {
            return null;
        }

        if (dateFormat == null ) {
            return xcal.toString();
        }
        else {
            Date d = xcal.toGregorianCalendar().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
            return sdf.format( d );
        }
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( Date date ) {
        if ( date == null ) {
            return null;
        }

        GregorianCalendar c = new GregorianCalendar();
        c.setTime( date );
        return datatypeFactory.newXMLGregorianCalendar( c );
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
}
