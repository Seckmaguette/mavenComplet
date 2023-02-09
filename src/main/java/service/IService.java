package service;

import models.Service;

import java.sql.SQLException;
import java.util.List;

public interface IService
{
    public Service add(Service service) throws SQLException;

    Service update(Service service, int type) throws SQLException;

    public Service findByName(String name) throws SQLException;
    public List<Service> findAll() throws SQLException;
    public void remove(int id) throws SQLException;



}
