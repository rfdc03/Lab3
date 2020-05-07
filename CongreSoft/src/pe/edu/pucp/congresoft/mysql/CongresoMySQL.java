/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.congresoft.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.congresoft.config.DBManager;
import pe.edu.pucp.congresoft.dao.CongresoDAO;
import pe.edu.pucp.congresoft.model.Congreso;

/**
 *
 * @author Rodrigo
 */
public class CongresoMySQL implements CongresoDAO {

    @Override
    public int insertar(Congreso congreso) {
        int resultado = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DBManager.urlMySQL,
                    DBManager.user,DBManager.password);
            String sentencia = "INSERT INTO CONGRESO(NOMBRE, FECHA_INICIO,"+
                    "FECHA_FIN, PAIS, ACTIVO) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sentencia);
            ps.setString(1,congreso.getNombre());
            ps.setDate(2,new java.sql.Date(congreso.getFecha_inicio().getTime()));
            ps.setDate(3,new java.sql.Date(congreso.getFecha_fin().getTime()));
            ps.setString(4,congreso.getNombre());
            ps.setBoolean(5,congreso.getActivo());
            
            resultado = ps.executeUpdate();
            
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public ArrayList<Congreso> listar() {
        ArrayList<Congreso> congresos = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DBManager.urlMySQL,
                    DBManager.user,DBManager.password);
            String sentencia = "SELECT * FROM CONGRESO";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            while(rs.next()){
                Congreso congreso = new Congreso();
                congreso.setId(rs.getInt("ID_CONGRESO"));
                congreso.setNombre(rs.getString("NOMBRE"));
                congreso.setPais(rs.getString("PAIS"));
                congreso.setFecha_inicio(rs.getDate("FECHA_INICIO"));
                congreso.setFecha_fin(rs.getDate("FECHA_FIN")); 
                congresos.add(congreso);
            }
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return congresos;
    }
    
}
