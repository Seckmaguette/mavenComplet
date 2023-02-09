package main;

import medecin.MedecinDao;
import models.Medecin;
import models.Service;
import models.Specialite;
import service.ServiceDao;
import specialite.SpecialiteDao;
import utils.KeyboardHelper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) throws SQLException {

        main2 m =new main2();
        MedecinDao medecinDao = new MedecinDao();
        ServiceDao serviceDao = new ServiceDao();
        SpecialiteDao specialiteDao = new SpecialiteDao();
        Service service = new Service();
        Medecin medecin = new Medecin();
        Specialite specialite = new Specialite();

        // Medecin medecin = new Medecin(1,"mat1","tening","ngom",LocalDate.of(2022,12,12),LocalDate.of(2022,12,12),2022,"Liberte 6");

        System.out.println("1-Medecin");
        System.out.println("2-Specialite");
        System.out.println("3-Service");
        System.out.println("4-Quiter");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if (choix == 1 || choix ==2 || choix ==3){
            String menu ;

            if (choix ==1){
                menu = "Medecin";
            }
          else if (choix ==2){
                menu = "Specialite";
            }
          else {
              menu = "Service";
            }
          m.sousMenu(menu);

            int sm = sc.nextInt();
            //Ajout
            if (sm == 1){
                if (menu =="Specialite"){
                    specialite.saisi(" à ajouter");
                    if (specialiteDao.findByName(specialite.getLibelle()) == null) {
                        specialiteDao.update(specialite, 1);
                        System.out.println("Specialité ajoutée avec succés");
                    } else {
                        System.out.println("Cette specialite  existe déjà");
                    }
                } else if (menu =="Service") {
                    service.saisi();
                    //serviceDao.update(service,0);
                    if (serviceDao.findByName(service.getLibelle()) == null) {
                        serviceDao.update(service, 1);
                        System.out.println("Service ajoutée avec succés");

                    } else {
                        System.out.println("ce service existe déjà");
                    }

                }
                else {
                    medecin.saisie();
                    service.saisi();
                    Service s1 = serviceDao.findByName(service.getLibelle());
                    specialite.saisi("du medecin");
                    Specialite sp2 = specialiteDao.findByName(specialite.getLibelle());
                    medecin.setService(s1);
                    medecin.setSpecialite(sp2);
                    medecinDao.update(medecin,1);
                }
                m.sousMenu(menu);
                int suite = sc.nextInt();

            }
            //Modification
            else if (sm == 2) {
                if (menu == "Specialite" ){
                    specialite.saisi("à modifier");

                    Specialite  specialite1 = specialiteDao.findByName(specialite.getLibelle());
                    specialite1.getId();
                    specialite1.saisi(" à remplacer par "+specialite1.getLibelle());
                    specialiteDao.update(specialite1,0);
                }
                else if (menu == "Service"){
                    service.saisi();
                    Service  s = serviceDao.findByName(service.getLibelle());
                    s.getId();
                    s.saisi();
                    serviceDao.update(s,0);
                }
                else {
                   // medecin.findMat();
                    Medecin medecin1 = medecinDao.findByMat(KeyboardHelper.saisi("Saisir le matricule à rechercher"));
                    updateFields(medecin1);
                    medecinDao.update(medecin1,0);
                    System.out.println("Modifié avec succes");
                }
                m.sousMenu(menu);
                int suite = sc.nextInt();

            }
            //Suppession
            else if (sm == 3) {
                if (menu == "Specialite" ){
                    specialite.saisi("supprimer");
                    Specialite   speRemove = specialiteDao.findByName(specialite.getLibelle());
                    serviceDao.remove(speRemove.getId());
                } else if (menu =="Servive") {
                    service.saisi();
                    Service serRmove = serviceDao.findByName(service.getLibelle());
                    serviceDao.remove(serRmove.getId());

                }
                else {
                    medecin.findMat();
                    Medecin medRemove = medecinDao.findByMat(medecin.getMatricule());
                    medecinDao.remove(medRemove.getId());
                }
                m.sousMenu(menu);
                int suite = sc.nextInt();
            }
            //Liste
            else if (sm ==4) {
                if (menu == "Specialite" ){

                }

            }
        }
        else {

            System.out.println("Quittez avec succés");
            System.exit(0);
        }




        //serviceDao.findAll().forEach(service1 -> System.out.println(service1.getId() + "-" + service1.getLibelle()));

    }

    public static void updateFields(Medecin medecin) throws SQLException {

     String prenom =   KeyboardHelper.saisi("Modifier le prenom ?\n");
     if (!prenom.isEmpty()){
         medecin.setPrenom(prenom);
     }
        String nom =   KeyboardHelper.saisi("Modifier le nom ?\n");
        if (!nom.isEmpty()){
            medecin.setNom(nom);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateNaissance =   KeyboardHelper.saisi("Modifier la date de naissance ?\n");

        if (!dateNaissance.isEmpty()){
            medecin.setDateNaissance(LocalDate.parse(dateNaissance,formatter));
        }
        String dateEmbauche = KeyboardHelper.saisi("Modifier la date d'embauche ?\n");
        if (!dateEmbauche.isEmpty()){
            medecin.setDateEmbauche(LocalDate.parse(dateEmbauche,formatter));
        }
        String salaire =   KeyboardHelper.saisi("Modifier le salaire ?\n");
        if (!salaire.isEmpty()){
            medecin.setSalaire(Integer.parseInt(salaire));
        }
        String adresse =   KeyboardHelper.saisi("Modifier l'adresse ?\n");
        if (!adresse.isEmpty()){
            medecin.setAdresse(adresse);
        }
       String service = KeyboardHelper.saisi("Modifier le service ?");
        if (!service.isEmpty()){
            ServiceDao serviceDao = new ServiceDao();
            List<Service> services = serviceDao.findAll();
            services.forEach(service1 -> System.out.println(service1.getId() + "-" + service1.getLibelle()));
            List<Integer> integers = services.stream().map(s->s.getId()).collect(Collectors.toList());
            Integer id = 0;
            do {
                id = Integer.parseInt(KeyboardHelper.saisi("Saisir le numero du service"));

            }while (!integers.contains(id));
            Service s  = new Service();
            s.setId(id);
            medecin.setService(s);
        }

        String specilaite = KeyboardHelper.saisi("Modifier la specialite ?");
        if (!specilaite.isEmpty()){
            SpecialiteDao specialiteDao = new SpecialiteDao();
            List<Specialite> specialites = specialiteDao.findAll();
            specialites.forEach(specialite1 -> System.out.println(specialite1.getId() + "-" + specialite1.getLibelle()));
            List<Integer> integers = specialites.stream().map(s->s.getId()).collect(Collectors.toList());
            int id = 0;
            do {
                id = Integer.parseInt(KeyboardHelper.saisi("Saisir le numero de la specialite"));

            }while (!integers.contains(id));
            Specialite sp = new Specialite();
            sp.setId(id);
            medecin.setSpecialite(sp);
        }
    }
}