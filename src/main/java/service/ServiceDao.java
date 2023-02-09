package service;

import models.Service;
import utils.DataBaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements IService{
    public ServiceDao() {
        db = new DataBaseHelper();

    }

    private DataBaseHelper db ;

    public Service add(Service service) throws SQLException {
        try {
            String sql = "insert into service values(null,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = {service.getLibelle()};
            db.addParameters(parameters);
            db.myExecuteUpdate();
            service.setId(db.getGeneratedId());
        return service;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Service update(Service service, int type) throws SQLException {
        try {

            String sql = type == 0 ? "update service set libelle = ? where id = ?" :  "insert into service values(null,?)";
            db.myPrepareStatement(sql);
            Object[] parameters = null;

            if (type == 0){
                parameters = new Object[2];
                parameters[0] = service.getLibelle().toLowerCase();
                parameters[1] = service.getId();

            }
            else {
                parameters = new Object[1];
                parameters[0] = service.getLibelle().toLowerCase();
            }
            db.addParameters(parameters);
            db.myExecuteUpdate();
            //service.setId(db.getGeneratedId());

            return service;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Service findByName(String name) throws SQLException {
        try {
            String sql = "SELECT * FROM service WHERE libelle = ?";
            db.myPrepareStatement(sql);
            db.addParameters(new Object[] {name.toLowerCase()});
            ResultSet rs = db.myExecuteQuery();
            Service service = null;
            if (rs.next()) {
                service = new Service(rs.getInt(1), rs.getString(2));
            }


            rs.close();
            return service;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Service> findAll() throws SQLException {
        try {
            String sql = "select * from service";
            db.myPrepareStatement(sql);
            db.myExecuteQuery();
            ResultSet rs = db.myExecuteQuery();
            List<Service> services = new ArrayList<>();
            while (rs.next()){
                Service s = new Service(rs.getInt(1) , rs.getString(2));
                services.add(s);
            }
            rs.close();

            return services;
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
