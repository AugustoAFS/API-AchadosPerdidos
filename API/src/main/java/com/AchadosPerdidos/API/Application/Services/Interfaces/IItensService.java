package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.ItensDTO;
import com.AchadosPerdidos.API.Application.DTOs.ItensListDTO;

public interface IItensService {
    ItensListDTO getAllItens();
    ItensDTO getItemById(int id);
    ItensDTO createItem(ItensDTO itensDTO);
    ItensDTO updateItem(int id, ItensDTO itensDTO);
    boolean deleteItem(int id);
    ItensListDTO getActiveItens();
    ItensListDTO getItensByStatus(int statusId);
    ItensListDTO getItensByUser(int userId);
    ItensListDTO getItensByCampus(int campusId);
    ItensListDTO getItensByLocal(int localId);
    ItensListDTO getItensByEmpresa(int empresaId);
    ItensListDTO searchItens(String searchTerm);
}
