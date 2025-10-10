package com.AchadosPerdidos.API.Application.Mapper;

import com.AchadosPerdidos.API.Application.DTOs.AuxStatusItemDTO;
import com.AchadosPerdidos.API.Application.DTOs.AuxStatusItemListDTO;
import com.AchadosPerdidos.API.Domain.Entity.Aux_Status_Item;
import org.springframework.stereotype.Component;

@Component
public class AuxStatusItemModelMapper {
    
    public AuxStatusItemDTO toDTO(Aux_Status_Item entity) { 
        if (entity == null) {
            return null;
        }
        
        return new AuxStatusItemDTO(
            entity.getId_Status_Item(),
            entity.getDescricao_Status_Item(),
            entity.getData_Cadastro(),
            entity.getFlg_Inativo()
        );
    }
    
    public Aux_Status_Item toEntity(AuxStatusItemDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Aux_Status_Item entity = new Aux_Status_Item();
        
        entity.setId_Status_Item(dto.Id_Status_Item());
        entity.setDescricao_Status_Item(dto.Descricao_Status_Item());
        entity.setData_Cadastro(dto.Data_Cadastro());
        entity.setFlg_Inativo(dto.Flg_Inativo());
        
        return entity;
    }

    public AuxStatusItemListDTO toListDTO(Aux_Status_Item entity) {
        if (entity == null) {
            return null;
        }

        return new AuxStatusItemListDTO(
            entity.getId_Status_Item(),
            entity.getDescricao_Status_Item(),
            entity.getData_Cadastro(),
            entity.getFlg_Inativo()
        );
    }
}
