package xaltius.azanespaul.ecom_api.users.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.dto.UsersDto;

@Component
public class UsersToUsersDtoConverter implements Converter<Users, UsersDto> {

    @Override
    public UsersDto convert(Users source) {

        final UsersDto usersDto = new UsersDto(source.getUsersId(),
                source.getName(),
                source.getAddress(),
                source.getEmail(),
                source.getMobile(),
                source.getRole());

        return usersDto;
    }
}
