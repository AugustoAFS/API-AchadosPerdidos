package com.AchadosPerdidos.API.Domain.Repository.Interfaces;

import com.AchadosPerdidos.API.Domain.Entity.Itens;
import java.util.List;

public interface IItensRepository {
    List<Itens> findAll();
    Itens findById(int id);
    Itens save(Itens itens);
    boolean deleteById(int id);
    List<Itens> findActive();
    List<Itens> findByStatus(int statusId);
    List<Itens> findByUser(int userId);
    List<Itens> findByCampus(int campusId);
    List<Itens> findByLocal(int localId);
    List<Itens> findByEmpresa(int empresaId);
    List<Itens> searchByTerm(String searchTerm);
}
