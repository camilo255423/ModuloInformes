package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

public class Facultad {
	
	String codFacultad;
	String nombre;

    
	
	public Facultad(String codFacultad, String nombre) {
		super();
		this.codFacultad = codFacultad;
		this.nombre = nombre;
	}
	public static List<Facultad> findAll()
	{
     	Connection con = DB.getConnection();
		List<Facultad> facultades = new ArrayList<Facultad>();
		PreparedStatement p;
		try {
			p = con.prepareStatement(consultaFacultades);
			
		
			ResultSet rs=p.executeQuery();
			while (rs.next()) {
				facultades.add(new Facultad(rs.getString("ID_DECAN"),
						rs.getString("DESCRIP")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Excepcion : "+e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	
		
		
		return facultades;
	}
	public static Facultad findById(String id)
	{
     	Connection con = DB.getConnection();
		Facultad facultad=null;
		PreparedStatement p;
		try {
			p = con.prepareStatement(consultaIdFacultad);
			
			p.setString(1, id);
		
			ResultSet rs=p.executeQuery();
			while (rs.next()) {
				facultad = new Facultad(rs.getString("ID_DECAN"),
						rs.getString("DESCRIP"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Excepcion : "+e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
	
		
		
		return facultad;
	}
	
	
	public String getCodFacultad() {
		return codFacultad;
	}
	public void setCodFacultad(String codFacultad) {
		this.codFacultad = codFacultad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	private static final String consultaFacultades ="select * from sai.SCT_DECAN where id_decan>=1 and id_decan<=4";
	private static final String consultaIdFacultad ="select * from sai.SCT_DECAN where id_decan=?";
	
}
