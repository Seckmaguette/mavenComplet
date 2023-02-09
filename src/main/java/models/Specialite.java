package models;

import java.util.Scanner;

public class Specialite {
    private int id;

    public Specialite(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    private String libelle;

    public Specialite() {
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

    public void saisi(String word) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Saisir le nom de la specialite  "+word);
            libelle = sc.nextLine();
        } while (libelle.isEmpty());
    }


}
