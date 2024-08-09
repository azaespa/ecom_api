package xaltius.azanespaul.ecom_api.system;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xaltius.azanespaul.ecom_api.customer.exception.CustomerNotFoundException;
import xaltius.azanespaul.ecom_api.product.exception.ProductInvalidSellerException;
import xaltius.azanespaul.ecom_api.product.exception.ProductNotFoundException;
import xaltius.azanespaul.ecom_api.seller.exception.SellerNotFoundException;
import xaltius.azanespaul.ecom_api.users.exception.UsersMobileAlreadyTakenException;
import xaltius.azanespaul.ecom_api.users.exception.UsersIdNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAuthenticationException(Exception ex) {
        return new Result("Mobile or Password is incorrect.", HttpStatus.UNAUTHORIZED.value(), null);
    }

    @ExceptionHandler({UsersIdNotFoundException.class,
            UsersMobileAlreadyTakenException.class,
            ProductNotFoundException.class,
            SellerNotFoundException.class,
            ProductInvalidSellerException.class,
            CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleUsersException(Exception ex) {
        return new Result(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
    }
}
