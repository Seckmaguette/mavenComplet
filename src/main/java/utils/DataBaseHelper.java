package utils;

import java.sql.*;

public class DataBaseHelper {
    private Connection cnx;
    private PreparedStatement pstmt;
    private void openConnection() throws SQLException {
        try {

            String url = "jdbc:mysql://localhost:8000/hospitalBD";
            String username = "root",password = "";
            if (cnx == null || cnx.isClosed()){
                cnx = DriverManager.getConnection(url,username,password);

            }


        }
        catch (Exception e){
            throw e;
        }
    }
    public  void myPrepareStatement(String sql) throws SQLException {
        try {
            openConnection();
            if (sql.trim().toLowerCase().startsWith("insert")){
                pstmt = cnx.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            }
            else {
                pstmt = cnx.prepareStatement(sql);
            }
        }
        catch (Exception e){
        throw e;
        }

    }
    public void addParameters(Object[] parameters) throws SQLException {
        try {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject((i+1),parameters[i]);
            }
        }
        catch (Exception e){
            throw e;
        }

    }
    public ResultSet myExecuteQuery() throws SQLException {
        try {

            return pstmt.executeQuery();
        }
        catch (Exception e){
            throw e;
        }
    }
    public int myExecuteUpdate() throws SQLException {
        try {

            return pstmt.executeUpdate();
        }
        catch (Exception e){
            throw e;
        }
    }
    public void closeConnection() throws SQLException {
        try {
            if (pstmt != null && !pstmt.isClosed()){
                pstmt.close();
                pstmt = null;
            }
            if (cnx != null && !cnx.isClosed()){
                cnx.close();
                cnx = null;
            }

        }
        catch (Exception e){
            throw e;
        }
    }
    public int getGeneratedId() throws SQLException {
        try {
            int id = 0;
        ResultSet rs = pstmt.getGeneratedKeys();
        while (rs.next()){
            id = rs.getInt(1);
        }
        rs.close();
        return id;
        }
        catch (Exception e){
            throw e;
        }
    }
}
