package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.AuxStatusItemDTO;
import com.AchadosPerdidos.API.Application.DTOs.AuxStatusItemListDTO;

import java.util.List;

public interface IAuxStatusItemService {
    
    AuxStatusItemListDTO criarAuxStatusItem(AuxStatusItemDTO dto);
    
    AuxStatusItemListDTO buscarPorId(int id);
    
    AuxStatusItemListDTO buscarPorDescricao(String descricao);
    
    List<AuxStatusItemListDTO> listarTodos();
    
    AuxStatusItemListDTO atualizarAuxStatusItem(int id, AuxStatusItemDTO dto);
    
    boolean deletarAuxStatusItem(int id);
}
