package xaltius.azanespaul.ecom_api.users.dto;

import jakarta.validation.constraints.NotEmpty;

public record UsersDto(
        Integer id,
        String name,
        String address,
        String email,

        @NotEmpty(message = "Mobile is required.")
        String mobile,

        @NotEmpty(message = "Role is required.")
        String role) {
}
