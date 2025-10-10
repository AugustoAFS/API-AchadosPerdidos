package com.AchadosPerdidos.API.Application.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({
    "Id_Status_Item",
    "Descricao_Status_Item",
    "Data_Cadastro",
    "Flg_Inativo"
})
public record AuxStatusItemDTO(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Integer Id_Status_Item,
    @JsonProperty("Descricao_Status_Item") String Descricao_Status_Item,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Date Data_Cadastro,
    @JsonProperty("Flg_Inativo") Boolean Flg_Inativo
) {}
