package com.AchadosPerdidos.API.Application.DTOs;

public record GoogleUserDTO(
    String id,
    String email,
    String name,
    String picture,
    String given_name,
    String family_name,
    boolean verified_email
) {}
