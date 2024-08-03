package xaltius.azanespaul.ecom_api.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import xaltius.azanespaul.ecom_api.users.MyUserPrincipal;
import xaltius.azanespaul.ecom_api.users.Users;
import xaltius.azanespaul.ecom_api.users.converter.UsersToUsersDtoConverter;
import xaltius.azanespaul.ecom_api.users.dto.UsersDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UsersToUsersDtoConverter usersToUsersDtoConverter;

    public AuthService(JwtProvider jwtProvider, UsersToUsersDtoConverter usersToUsersDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.usersToUsersDtoConverter = usersToUsersDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create user info.
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        Users users = principal.getUsers();
        UsersDto usersDto = usersToUsersDtoConverter.convert(users);

        // Create a JWT.
        String token = jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", usersDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
