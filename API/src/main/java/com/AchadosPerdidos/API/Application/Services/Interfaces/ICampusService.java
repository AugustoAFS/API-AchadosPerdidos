package com.AchadosPerdidos.API.Application.Services.Interfaces;

import com.AchadosPerdidos.API.Application.DTOs.CampusDTO;
import com.AchadosPerdidos.API.Application.DTOs.CampusListDTO;

public interface ICampusService {
    CampusListDTO getAllCampus();
    CampusDTO getCampusById(int id);
    CampusDTO createCampus(CampusDTO campusDTO);
    CampusDTO updateCampus(int id, CampusDTO campusDTO);
    boolean deleteCampus(int id);
    CampusListDTO getActiveCampus();
    CampusListDTO getCampusByInstitution(int institutionId);
}
