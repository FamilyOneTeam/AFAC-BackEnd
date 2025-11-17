package cl.somosafac.afacbackend.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private Boolean aceptarTerminos;

    // Manual getters for Lombok compatibility issue
    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public Boolean getAceptarTerminos() {
        return aceptarTerminos;
    }
}
