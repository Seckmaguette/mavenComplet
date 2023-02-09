package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Medecin {
    private  int id;

    private  String matricule;
    public Medecin() {
    }
    private  String nom;
    private  String prenom;
    private LocalDate dateNaissance;
    private LocalDate dateEmbauche;
    private int salaire;
    private String adresse;
    private Service service;
    private Specialite specialite;


    public Medecin(int id, String matricule, String nom, String prenom, LocalDate dateNaissance, LocalDate dateEmbauche, int salaire, String adresse) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.adresse = adresse;
    }



    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public void saisie(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer le nom du medecin ");
        nom = sc.nextLine();
        System.out.println("Veuillez entrer le prenom du medecin ");
        prenom = sc.nextLine();
        System.out.println("Veuillez  entrer le matricule du medecin ");
        matricule = sc.nextLine();
        System.out.println("Veuillez entrer la date de naissance du medecin à ajouter commme ceci 'AA-MM-dd'");
        dateNaissance = LocalDate.parse(sc.nextLine(),formatter);
        System.out.println("Veuillez entrer la date d'embauche du medecin à ajouter commme ceci 'AA-MM-dd'");
        dateEmbauche  = LocalDate.parse(sc.nextLine(),formatter);
        System.out.println("Veuillez  entrer le salaire du medecin ");
        salaire = sc.nextInt();
        System.out.println("Veuillez  entrer l'adresse du medecin ");
        adresse = sc.next();
    }
    public void findMat(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrez le matricule du medecin ");
        matricule = sc.nextLine();
    }

}
