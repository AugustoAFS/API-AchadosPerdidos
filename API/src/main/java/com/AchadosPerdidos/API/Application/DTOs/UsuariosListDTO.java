package com.AchadosPerdidos.API.Application.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosListDTO {
    private List<UsuariosDTO> usuarios;
    private int totalCount;
}