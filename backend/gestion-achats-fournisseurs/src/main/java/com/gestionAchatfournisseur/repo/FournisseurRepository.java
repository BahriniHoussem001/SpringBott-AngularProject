package com.gestionAchatfournisseur.repo;

import java.beans.JavaBean;

//import org.glassfish.jaxb.core.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionAchatfournisseur.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
