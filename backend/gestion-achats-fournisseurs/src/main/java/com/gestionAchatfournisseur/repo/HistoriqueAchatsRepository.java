package com.gestionAchatfournisseur.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionAchatfournisseur.entity.HistoriqueAchats;

public interface HistoriqueAchatsRepository extends JpaRepository<HistoriqueAchats, Long> {
	List<HistoriqueAchats> findByProduit(String produit);

}
