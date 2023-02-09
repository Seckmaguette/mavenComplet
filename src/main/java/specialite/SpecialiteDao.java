package specialite;

import models.Service;
import models.Specialite;
import utils.DataBaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteDao implements ISepecialite{
    private DataBaseHelper db ;
    public SpecialiteDao() {
        db = new DataBaseHelper();
    }
    @Override
    public Specialite add(Specialite specialite) throws SQLException {
        try {
            String sql = "insert into specialite values(null,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = {specialite.getLibelle()};
            db.addParameters(parameters);
            db.myExecuteUpdate();
            specialite.setId(db.getGeneratedId());
            return specialite;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Specialite update(Specialite specialite, int type) throws SQLException {
        try {

            String sql = type == 0 ? "update specialite set libelle = ? where id = ?" :  "insert into specialite values(null,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = null;

            if (type == 0){
                parameters = new Object[2];
                parameters[0] = specialite.getLibelle().toLowerCase();
                parameters[1] = specialite.getId();

            }
            else {
                parameters = new Object[1];
                parameters[0] = specialite.getLibelle().toLowerCase();
            }
            db.addParameters(parameters);
            db.myExecuteUpdate();
            //service.setId(db.getGeneratedId());

            return specialite;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Specialite findByName(String name) throws SQLException {
        try {
            String sql = "SELECT * FROM specialite WHERE libelle = ?";
            db.myPrepareStatement(sql);
            db.addParameters(new Object[] {name.toLowerCase()});
            ResultSet rs = db.myExecuteQuery();
            Specialite specialite = null;
            if (rs.next()) {
                specialite = new Specialite(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            return specialite;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Specialite> findAll() throws SQLException {
        try {
            String sql = "select * from specialite";
            db.myPrepareStatement(sql);
            db.myExecuteQuery();
            ResultSet rs = db.myExecuteQuery();
            List<Specialite> specialites = new ArrayList<>();
            while (rs.next()){
                Specialite s = new Specialite(rs.getInt(1) , rs.getString(2));
                specialites.add(s);
            }
            rs.close();

            return specialites;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        try {
            String sql = "delete from specialite where id = ?";
            db.myPrepareStatement(sql);
            db.addParameters(new Object[]{id});
            db.myExecuteUpdate();


        }
        catch (Exception e){
            throw e;
        }

    }
}
