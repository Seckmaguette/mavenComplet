package specialite;

import models.Specialite;

import java.sql.SQLException;
import java.util.List;

public interface ISepecialite {
    public Specialite add(Specialite specialite) throws SQLException;

    Specialite update(Specialite specialite, int type) throws SQLException;

    public Specialite findByName(String name) throws SQLException;
    public List<Specialite> findAll() throws SQLException;
    public void remove(int id) throws SQLException;
}
