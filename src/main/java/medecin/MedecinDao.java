package medecin;

import models.Medecin;
import models.Service;
import models.Specialite;
import utils.DataBaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MedecinDao implements IMedecin{
 private DataBaseHelper db;
    public MedecinDao() {
        db = new DataBaseHelper();
    }

    @Override

    public Medecin add(Medecin medecin) throws SQLException {
        try {
            String sql = "insert into medecin values(null,?,?,?,?,?,?,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = {medecin.getMatricule(),medecin.getNom(),medecin.getPrenom(),medecin.getDateNaissance()
                    ,medecin.getDateEmbauche(),medecin.getSalaire(),medecin.getAdresse()};
            db.addParameters(parameters);
            db.myExecuteUpdate();
            medecin.setId(db.getGeneratedId());
            return medecin;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Medecin update(Medecin medecin,int type) throws SQLException {
        try {

            String sql = type == 0 ? "update medecin set nom = ?, prenom = ? , matricule = ? , dateNaissance = ? , dateEmbauche = ? , salaire = ? , adresse = ?,service_id = ? ,specialite_id = ? where id = ?" :  "insert into medecin values(null,?,?,?,?,?,?,?,?,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = null;

            if (type == 0){
                parameters = new Object[10];
                parameters[0] =  medecin.getNom();
                parameters[1] = medecin.getPrenom();
                parameters[2] = medecin.getMatricule();
                parameters[3] = medecin.getDateNaissance();
                parameters[4] = medecin.getDateEmbauche();
                parameters[5] = medecin.getSalaire();
                parameters[6] = medecin.getAdresse();
                parameters[7] = medecin.getService().getId();
                parameters[8] = medecin.getSpecialite().getId();
                parameters[9] = medecin.getId();




            }
            else {
                parameters = new Object[9];
                parameters[0] = medecin.getMatricule().toLowerCase();
                parameters[1] = medecin.getNom();
                parameters[2] = medecin.getPrenom();
                parameters[3] = medecin.getDateNaissance();
                parameters[4] = medecin.getDateEmbauche();
                parameters[5] = medecin.getSalaire();
                parameters[6] = medecin.getAdresse();
                parameters[7] = medecin.getService().getId();
                parameters[8] = medecin.getSpecialite().getId();
            }
            db.addParameters(parameters);
            db.myExecuteUpdate();
            //service.setId(db.getGeneratedId());

            return medecin;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Medecin findByMat(String name) throws SQLException {
        try {
            String sql = "SELECT * FROM medecin WHERE matricule = ?";
            db.myPrepareStatement(sql);
            db.addParameters(
                    new Object[] {name.toLowerCase()}
            );
            ResultSet rs = db.myExecuteQuery();
            Medecin medecin = null;
            if (rs.next()) {
                medecin = new Medecin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate(),rs.getInt(7),rs.getString(8));
            }
            rs.close();
            return medecin;
        } catch (Exception e) {
            throw e;
        }
    }

    public Medecin add(Medecin medecin, Specialite specialite,Service service) throws SQLException {
        try {
            String sql = "insert into medecin values(null,?,?,?,?,?,?,?,?,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = {medecin.getMatricule(),medecin.getNom(),medecin.getPrenom(),medecin.getDateNaissance()
                    ,medecin.getDateEmbauche(),medecin.getSalaire(),medecin.getAdresse(),specialite.getId(),service.getId()};
            db.addParameters(parameters);
            db.myExecuteUpdate();
            medecin.setId(db.getGeneratedId());
            return medecin;
        }
        catch (Exception e){
            throw e;
        }
    }

    /*  public Medecin update(Medecin medecin, int type) throws SQLException {
        try {

            String sql = type == 0 ? "update medecin set nom = ? where id = ?" :  "insert into medecin values(null,?,?,?,?,?,?,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = null;

            if (type == 0){
                parameters = new Object[2];
                parameters[0] = medecin.getMatricule().toLowerCase();
                parameters[1] = medecin.getId();

            }
            else {
                parameters = new Object[7];
                parameters[0] = medecin.getMatricule().toLowerCase();
                parameters[1] = medecin.getNom();
                parameters[2] = medecin.getPrenom();
                parameters[3] = medecin.getDateNaissance();
                parameters[4] = medecin.getDateEmbauche();
                parameters[5] = medecin.getSalaire();
                parameters[6] = medecin.getAdresse();
            }
            db.addParameters(parameters);
            db.myExecuteUpdate();
            //service.setId(db.getGeneratedId());

            return medecin;
        }
        catch (Exception e){
            throw e;
        }
    }*/



    @Override
    public List<Medecin> findAll() {
        return null;
    }

    @Override
    public void remove(int id) throws SQLException {
        try {
            String sql = "delete from medecin where matricule = ?";
            db.myPrepareStatement(sql);
            db.addParameters(new Object[]{id});
            db.myExecuteUpdate();


        }
        catch (Exception e){
            throw e;
        }
    }
}
