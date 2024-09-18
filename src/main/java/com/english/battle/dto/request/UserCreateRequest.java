package com.english.battle.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequest {
    private String username;
    private String password;
    private String address;
    private String phone;
    private String fullName;
    private String DayOfBirth;
    private String Gender;
    private boolean isHide;
    private String status;
}
