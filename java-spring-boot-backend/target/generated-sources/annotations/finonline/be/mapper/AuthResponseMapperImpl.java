package finonline.be.mapper;

import finonline.be.io.swagger.model.AuthResponse;
import finonline.be.web.response.UserAuthResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T09:00:50+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class AuthResponseMapperImpl implements AuthResponseMapper {

    @Override
    public AuthResponse toAuthResponse(UserAuthResponse userAuthResponse) {
        if ( userAuthResponse == null ) {
            return null;
        }

        AuthResponse authResponse = new AuthResponse();

        authResponse.setId( userAuthResponse.getId() );
        authResponse.setName( userAuthResponse.getName() );
        authResponse.setToken( userAuthResponse.getToken() );

        return authResponse;
    }
}
