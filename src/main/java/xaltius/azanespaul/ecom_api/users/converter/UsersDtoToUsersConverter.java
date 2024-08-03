package xaltius.azanespaul.ecom_api.users.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.dto.UsersDto;

@Component
public class UsersDtoToUsersConverter implements Converter<UsersDto, Users> {

    @Override
    public Users convert(UsersDto source) {
        Users users = new Users();
        users.setMobile(source.mobile());
        users.setRole(source.role());
        return users;
    }
}
