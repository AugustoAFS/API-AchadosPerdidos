package com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces;

public interface IAuxLocalItemQueries {
    
    String getInsertAuxLocalItem();
    
    String getSelectById();
    
    String getSelectByNome();
    
    String getSelectAll();
    
    String getUpdateAuxLocalItem();
    
    String getDeleteAuxLocalItem();
}
