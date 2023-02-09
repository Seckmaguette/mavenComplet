package models;

import java.util.Collection;
import java.util.Scanner;

public class Service {
    private int id;
    private String libelle;

    private Collection<Medecin> medecins;
    private Collection<Specialite> specialites;

    public Collection<Specialite> specialites() {
        return specialites;
    }

    public Service(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public void setSpecialites(Collection<Specialite> specialites) {
        this.specialites = specialites;
    }

    public Collection<Medecin> medecins() {
        return medecins;
    }

    public void setMedecins(Collection<Medecin> medecins) {
        this.medecins = medecins;
    }

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void saisi() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Saisir le nom du service");
            libelle = sc.nextLine();
        } while (libelle.isEmpty());
    }
  /*  public void saisieUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("entrer l'id à modifier");
        id = scanner.nextInt();
    }*/
 }
