package fr.adriencaubel.jpa.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    @OneToMany(mappedBy="promotion", cascade=CascadeType.ALL)
    private List<Cours> listesCours;

    // Constructors
    public Promotion() {}

    public Promotion(String libelle) {
        this.libelle = libelle;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Cours> getListesCours() {
        return listesCours;
    }

    public void setListesCours(List<Cours> listesCours) {
    	for(Cours cours : listesCours) {
    		cours.setPromotion(this);
    	}
        this.listesCours = listesCours;
    }
}

