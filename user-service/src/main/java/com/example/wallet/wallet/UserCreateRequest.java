package com.example.wallet.wallet;


//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber; //will be acting as userName in case spring security
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String country;
    private String dob;
    @NotBlank
    private String identifierValue;

    @NotNull
    private UserIdentifier userIdentifier;

    public User to(){
        return User.builder()
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .password(this.password)
                .email(this.email)
                .country(this.country)
                .dob(this.dob)
                .userIdentifier(this.userIdentifier)
                .identifierValue(this.identifierValue)
                .build();
    }



}
