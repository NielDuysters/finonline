package finonline.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import finonline.be.io.swagger.model.AuthResponse;
import finonline.be.web.response.UserAuthResponse;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthResponseMapper {
	
	AuthResponseMapper INSTANCE = Mappers.getMapper(AuthResponseMapper.class);

	@Mapping(source = "userAuthResponse.id", target = "id")
    @Mapping(source = "userAuthResponse.name", target = "name")
    @Mapping(source = "userAuthResponse.token", target = "token")
    AuthResponse toAuthResponse(UserAuthResponse userAuthResponse);
}
