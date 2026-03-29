package com.gestionAchatfournisseur.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionAchatfournisseur.entity.CommandeAchat;

public interface CommandeAchatRepository extends JpaRepository<CommandeAchat, Long> {
	List<CommandeAchat> findByFournisseurId(Long fournisseurId);

}
