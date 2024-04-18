package finonline.be.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import finonline.be.domain.model.User;
import finonline.be.web.request.PatchUser;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "user.id", target = "id")
	@Mapping(source = "user.name", target = "name")
	@Mapping(source = "user.startCapital", target = "startCapital")
	@Mapping(source = "user.currentCapital", target = "currentCapital")
	finonline.be.io.swagger.model.User toUserResponse(User user);
	
	@Mapping(source = "body.newStartCapital", target = "newStartCapital")
	PatchUser userBodyToPatchUser(finonline.be.io.swagger.model.UsersBody body);
}
