package com.book.mappers;



import com.book.dto.TokenDTO;
import com.book.entity.Roles;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

    public TokenDTO tokenDTO(String token, Long id, String email, String name, String address, String tele, Roles role){
        return new TokenDTO(
                token,
                id,
                email,
                name,
                address,
                tele,
                role
        );
    }
}

