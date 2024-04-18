package finonline.be.mapper;

import finonline.be.domain.model.User;
import finonline.be.io.swagger.model.UsersBody;
import finonline.be.web.request.PatchUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T09:00:50+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public finonline.be.io.swagger.model.User toUserResponse(User user) {
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

    @Override
    public PatchUser userBodyToPatchUser(UsersBody body) {
        if ( body == null ) {
            return null;
        }

        PatchUser patchUser = new PatchUser();

        patchUser.setNewStartCapital( body.getNewStartCapital() );

        return patchUser;
    }
}
