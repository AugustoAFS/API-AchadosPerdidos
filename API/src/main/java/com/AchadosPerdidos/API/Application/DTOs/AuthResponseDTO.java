package com.AchadosPerdidos.API.Application.DTOs;

public record AuthResponseDTO(
    String Token,
    UserInfoDTO UserInfo
) {
    
    public record UserInfoDTO(
        int Id_User_Info,
        String Name,
        String Email,
        String Role
    ) {
    }
}
