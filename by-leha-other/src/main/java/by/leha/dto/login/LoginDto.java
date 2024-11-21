package by.leha.dto.login;



import by.leha.dto.utils.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Set<RoleEnum> roles = new HashSet<>();
}
