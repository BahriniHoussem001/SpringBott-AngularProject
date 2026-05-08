package com.gestionAchatfournisseur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueAchats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    @NotNull(message = "Le fournisseur est obligatoire")
    private Fournisseur fournisseur;

    @NotBlank(message = "Le produit est obligatoire")
    private String produit;

    @NotNull(message = "La quantité est obligatoire")
    private Integer quantite;

    @NotNull(message = "Le délai de livraison est obligatoire")
    private Integer delaiLivraison;
}