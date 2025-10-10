package com.AchadosPerdidos.API.Infrastruture.Mysql;
        
import com.AchadosPerdidos.API.Infrastruture.Mysql.Interfaces.IAuxStatusItemQueries;
import org.springframework.stereotype.Component;

@Component
public class AuxStatusItemQueries implements IAuxStatusItemQueries {
    
    private static final String SQL_INSERT_AUX_STATUS_ITEM = """
            INSERT INTO Aux_Status_Item (
                Descricao_Status_Item, Data_Cadastro, Flg_Inativo
            ) VALUES (?, ?, ?)
            """;
    
    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM Aux_Status_Item WHERE Id_Status_Item = ?
            """;
    
    private static final String SQL_SELECT_BY_DESCRICAO = """
            SELECT * FROM Aux_Status_Item WHERE Descricao_Status_Item = ?
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM Aux_Status_Item ORDER BY Descricao_Status_Item
            """;

    private static final String SQL_UPDATE_AUX_STATUS_ITEM = """
            UPDATE Aux_Status_Item SET 
                Descricao_Status_Item = ?, Flg_Inativo = ?
            WHERE Id_Status_Item = ?
            """;
    
    private static final String SQL_DELETE_AUX_STATUS_ITEM = "DELETE FROM Aux_Status_Item WHERE Id_Status_Item = ?";
    
    @Override
    public String getInsertAuxStatusItem() {
        return SQL_INSERT_AUX_STATUS_ITEM;
    }
        
    @Override
    public String getSelectById() {
        return SQL_SELECT_BY_ID;
    }
    
    @Override
    public String getSelectByDescricao() {
        return SQL_SELECT_BY_DESCRICAO;
    }
    
    @Override
    public String getSelectAll() {
        return SQL_SELECT_ALL;
    }
    
    @Override
    public String getUpdateAuxStatusItem() {
        return SQL_UPDATE_AUX_STATUS_ITEM;
    }
    
    @Override
    public String getDeleteAuxStatusItem() {
        return SQL_DELETE_AUX_STATUS_ITEM;
    }
}
