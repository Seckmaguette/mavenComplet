package medecin;

import models.Medecin;
import models.Service;
import models.Specialite;

import java.sql.SQLException;
import java.util.List;

public interface IMedecin {
    Medecin add(Medecin medecin) throws SQLException;
    //Medecin update(Medecin medecin,int type) throws SQLException;
    Medecin update(Medecin medecin,int type) throws SQLException;
    Medecin findByMat(String name) throws SQLException;
    List<Medecin> findAll();
    void remove(int id) throws SQLException;
}
