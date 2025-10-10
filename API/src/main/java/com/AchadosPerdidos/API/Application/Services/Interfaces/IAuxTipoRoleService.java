package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.AuxTipoRoleDTO;
import com.AchadosPerdidos.API.Application.DTOs.AuxTipoRoleListDTO;

public interface IAuxTipoRoleService {
    AuxTipoRoleListDTO getAllAuxTipoRoles();
    AuxTipoRoleDTO getAuxTipoRoleById(int id);
    AuxTipoRoleDTO createAuxTipoRole(AuxTipoRoleDTO auxTipoRoleDTO);
    AuxTipoRoleDTO updateAuxTipoRole(int id, AuxTipoRoleDTO auxTipoRoleDTO);
    boolean deleteAuxTipoRole(int id);
    AuxTipoRoleListDTO getActiveAuxTipoRoles();
}
