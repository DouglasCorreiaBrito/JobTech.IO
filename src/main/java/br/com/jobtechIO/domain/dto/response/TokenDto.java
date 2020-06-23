package br.com.jobtechIO.domain.dto.response;

import lombok.Data;

@Data
public class TokenDto {

    private String token;
    private String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
