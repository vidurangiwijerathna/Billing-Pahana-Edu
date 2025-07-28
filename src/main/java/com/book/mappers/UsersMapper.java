package com.book.mappers;

import com.book.dto.UsersDTO;
import com.book.dto.requestDTO.RegistrationOrLoginAdminsDTO;
import com.book.dto.requestDTO.RegistrationOrLoginAdminsDTO;
import com.book.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public Users dtoToEntityForRegOrLogin(RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO){
        return new Users(
                registrationOrLoginAdminsDTO.email(),
                registrationOrLoginAdminsDTO.name(),
                registrationOrLoginAdminsDTO.address(),
                registrationOrLoginAdminsDTO.tele(),
                registrationOrLoginAdminsDTO.password(),
                registrationOrLoginAdminsDTO.role()
        );
    }

    public UsersDTO EntityToDTO(Users user){
        return new UsersDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getAddress(),
                user.getTele()
        );
    }
}

