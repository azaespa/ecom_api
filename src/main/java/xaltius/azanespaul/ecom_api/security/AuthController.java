package xaltius.azanespaul.ecom_api.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xaltius.azanespaul.ecom_api.system.Result;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/seller")
    public Result getSellerLoginInfo(Authentication authentication) {
        LOGGER.debug("Authenticated user: '{}'", authentication.getName());
        return new Result("User Info and JSON Web Token", HttpStatus.OK.value(), authService.createLoginInfo(authentication));
    }

    @PostMapping("/login/customer")
    public Result getCustomerLoginInfo(Authentication authentication) {
        LOGGER.debug("Authenticated user: '{}'", authentication.getName());
        return new Result("User Info and JSON Web Token", HttpStatus.OK.value(), authService.createLoginInfo(authentication));
    }

//    @PostMapping("/logout/customer")
//    public String logoutCustomer(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        this.logoutHandler.logout(request, response, authentication);
//        return "redirect:/home";
//    }
}
