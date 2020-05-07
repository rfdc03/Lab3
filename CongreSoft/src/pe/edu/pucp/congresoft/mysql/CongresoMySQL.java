/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.congresoft.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.congresoft.dao.CongresoDAO;
import pe.edu.pucp.congresoft.model.Congreso;

/**
 *
 * @author Rodrigo
 */
public class CongresoMySQL implements CongresoDAO {

    @Override
    public int insertar(Congreso congreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Congreso> listar() {
        ArrayList<Congreso> congresos = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://"+
                    "lp2mysql.cdjj2wtonimd.us-east-1.rds.amazonaws.com"+
                    ":3306/inf282", "admin", "abcd1234");
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
            }
            con.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return congresos;
    }
    
}
