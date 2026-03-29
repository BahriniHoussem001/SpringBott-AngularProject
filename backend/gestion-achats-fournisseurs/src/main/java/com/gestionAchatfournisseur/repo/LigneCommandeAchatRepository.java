package com.gestionAchatfournisseur.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionAchatfournisseur.entity.LigneCommandeAchat;

public interface LigneCommandeAchatRepository extends JpaRepository<LigneCommandeAchat, Long> {

}
