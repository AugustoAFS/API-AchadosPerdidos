package com.AchadosPerdidos.API.Infrastruture.Mysql;

import com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces.IAuxLocalItemQueries;
import org.springframework.stereotype.Component;

@Component
public class AuxLocalItemQueries implements IAuxLocalItemQueries {
    
    private static final String SQL_INSERT_AUX_LOCAL_ITEM = """
            INSERT INTO Aux_Local_Item (
                Nome_Local_Item, Descricao_Local_Item, Data_Cadastro_Local_Item, Flg_Inativo_Local_Item
            ) VALUES (?, ?, ?, ?)
            """;
    
    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM Aux_Local_Item WHERE Id_Aux_Local_Item = ?
            """;
    
    private static final String SQL_SELECT_BY_NOME = """
            SELECT * FROM Aux_Local_Item WHERE Nome_Local_Item = ?
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM Aux_Local_Item ORDER BY Nome_Local_Item
            """;

    private static final String SQL_UPDATE_AUX_LOCAL_ITEM = """
            UPDATE Aux_Local_Item SET 
                Nome_Local_Item = ?, Descricao_Local_Item = ?, Flg_Inativo_Local_Item = ?
            WHERE Id_Aux_Local_Item = ?
            """;
    
    private static final String SQL_DELETE_AUX_LOCAL_ITEM = "DELETE FROM Aux_Local_Item WHERE Id_Aux_Local_Item = ?";
    
    @Override
    public String getInsertAuxLocalItem() {
        return SQL_INSERT_AUX_LOCAL_ITEM;
    }
        
    @Override
    public String getSelectById() {
        return SQL_SELECT_BY_ID;
    }
    
    @Override
    public String getSelectByNome() {
        return SQL_SELECT_BY_NOME;
    }
    
    @Override
    public String getSelectAll() {
        return SQL_SELECT_ALL;
    }
    
    @Override
    public String getUpdateAuxLocalItem() {
        return SQL_UPDATE_AUX_LOCAL_ITEM;
    }
    
    @Override
    public String getDeleteAuxLocalItem() {
        return SQL_DELETE_AUX_LOCAL_ITEM;
    }
}