package com.gestionAchatfournisseur.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFournisseur {

    private Long id;

    private String nom;

    private Double note;

    private String qualiteService;

    private Double delaiMoyenLivraison;

    private String efficacite;
}