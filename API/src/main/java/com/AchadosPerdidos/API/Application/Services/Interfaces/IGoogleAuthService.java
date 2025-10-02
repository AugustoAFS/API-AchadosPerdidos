package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.GoogleUserDTO;

public interface IGoogleAuthService {

    String buildGoogleAuthorizationUrl();

    GoogleUserDTO getUserInfoFromAuthorizationCode(String code);

    String getRedirectUri();
}