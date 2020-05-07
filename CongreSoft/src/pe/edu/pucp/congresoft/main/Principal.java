/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.congresoft.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.congresoft.dao.CongresoDAO;
import pe.edu.pucp.congresoft.model.Congreso;
import pe.edu.pucp.congresoft.mysql.CongresoMySQL;

/**
 *
 * @author Rodrigo
 */
public class Principal {
    public static void main(String[] args){
        ArrayList<Congreso> congresos = new ArrayList<>();
        
        CongresoDAO daoCongreso = 
                new CongresoMySQL();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try{
            Congreso congreso = new Congreso("INTERACT 2021", sdf.parse("01-01-2021"),
                sdf.parse("05-01-2021"),"BOLIVIA",true);
            int i = daoCongreso.insertar(congreso);
            if(i==1) System.out.println("Se ha insertado de manera exitosa");
            else System.out.println("Ha ocurrido un error");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        congresos = daoCongreso.listar();
        for(Congreso c : congresos){
            System.out.println(c.mostrarDatos());
        }
    }
}
