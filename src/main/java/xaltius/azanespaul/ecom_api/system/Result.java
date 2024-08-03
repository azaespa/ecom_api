package xaltius.azanespaul.ecom_api.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String message;
    private int statusCode;
    private Object data;
}
