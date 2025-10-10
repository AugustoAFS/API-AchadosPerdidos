package com.AchadosPerdidos.API.Application.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
    "Id_Aux_Local_Item",
    "Nome_Local_Item",
    "Descricao_Local_Item",
    "Data_Cadastro_Local_Item",
    "Flg_Inativo_Local_Item"
})
public record AuxLocalItemDTO(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Integer Id_Aux_Local_Item,
    @JsonProperty("Nome_Local_Item") String Nome_Local_Item,
    @JsonProperty("Descricao_Local_Item") String Descricao_Local_Item,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Date Data_Cadastro_Local_Item,
    @JsonProperty("Flg_Inativo_Local_Item") Boolean Flg_Inativo_Local_Item
) {}
