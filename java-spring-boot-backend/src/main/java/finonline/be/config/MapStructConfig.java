package finonline.be.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Configuration
@ComponentScan(basePackages = "finonline.be.mapper")
public class MapStructConfig {
    // Additional MapStruct configuration can be added here
}