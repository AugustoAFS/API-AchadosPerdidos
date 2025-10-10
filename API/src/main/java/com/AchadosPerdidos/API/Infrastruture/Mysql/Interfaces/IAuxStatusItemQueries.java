package com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces;

public interface IAuxStatusItemQueries {
    
    String getInsertAuxStatusItem();
    
    String getSelectById();
    
    String getSelectByDescricao();
    
    String getSelectAll();
    
    String getUpdateAuxStatusItem();
    
    String getDeleteAuxStatusItem();
}
