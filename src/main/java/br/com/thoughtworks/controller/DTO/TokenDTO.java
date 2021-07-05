package br.com.thoughtworks.controller.DTO;

public class TokenDTO {

    private String token;
    private String type;

    public TokenDTO(String generatedToken, String type) {
        this.token = generatedToken;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
