package com.english.battle.dto.response;

import com.english.battle.models.Roles;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private String username;
    private String password;
    private String address;
    private String phone;
    private String fullName;
    private String DayOfBirth;
    private String Gender;
    private boolean isHide;
    private String status;
    private Roles role;
}
