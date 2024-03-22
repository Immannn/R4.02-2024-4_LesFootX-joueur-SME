package org.univ_paris8.iut.montreuil.qdev.tp2024.gr4.LesFootX.services.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.univ_paris8.iut.montreuil.qdev.tp2024.gr4.LesFootX.entities.JoueurDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2024.gr4.LesFootX.services.interfaces.IServicesJoueur;
import org.univ_paris8.iut.montreuil.qdev.tp2024.gr4.LesFootX.utiles.Enum.Langues;
import org.univ_paris8.iut.montreuil.qdev.tp2024.gr4.LesFootX.utiles.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class ServicesJoueur implements IServicesJoueur {
    private static ServicesJoueur uniqueInstance = null ;
    ArrayList<JoueurDTO> joueursDTO ;

    public ServicesJoueur() {
        this.joueursDTO = new ArrayList<>();
    }

    public static ServicesJoueur getInstance(){
        if(uniqueInstance == null)
            uniqueInstance = new ServicesJoueur();
        return uniqueInstance;
    }

    @Override
    public JoueurDTO ajouterJoueur(String prenom, String pseudo, int annéeNaissance, Enum langue, HashSet<String> centresInteret) throws JoueurDejaExistantException, AnneeNaissanceInvalideException, FormatLangueInvalideException, CentresInteretVidesException, NomOuPseudoLongueurException {
        for (JoueurDTO joueur : joueursDTO) {
            if (joueur.getPseudo().equals(pseudo)) {
                throw new JoueurDejaExistantException();
            }
        }
        for (JoueurDTO joueur : joueursDTO) {
            if (joueur.getPseudo().length()<3 || joueur.getNom().length()<3 ) {
                throw new NomOuPseudoLongueurException();
            }
        }

        if (annéeNaissance < 1900 || annéeNaissance > 2024) {
            throw new AnneeNaissanceInvalideException();
        }

        if (langue == null) {
            throw new FormatLangueInvalideException();
        }

        if (centresInteret.isEmpty()) {
            throw new CentresInteretVidesException();
        }

        JoueurDTO nouveauJoueur = new JoueurDTO(prenom, pseudo, annéeNaissance, langue, centresInteret);
        joueursDTO.add(nouveauJoueur);
        return nouveauJoueur;
    }

}

