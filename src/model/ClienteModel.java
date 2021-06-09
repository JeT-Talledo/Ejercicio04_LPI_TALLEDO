package model;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.TipoCliente;
import util.MySqlDBConexion;

public class ClienteModel {

	public List<Cliente> consultaCliente(String nombre){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "select c.idCliente, c.nombres, c.apellidos,c.fechaNacimiento, t.nombre\r\n" + 
					"from cliente c inner join tipo_cliente t \r\n" + 
					"on c.idTipoCliente = t.idTpoCliente where c.nombres = ?";
		
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			
			rs = pstm.executeQuery();
					
			Cliente objCliente = null;
			TipoCliente objTipoCliente = null;
			while(rs.next()){
			
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setFechaNacimiento(rs.getDate(4));
				
				objTipoCliente = new TipoCliente();
				objTipoCliente.setnombre(rs.getString(5));			

				objCliente.setIdTpoCliente(objTipoCliente);
				
				data.add(objCliente);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public List<Cliente> listaCliente(){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "select c.idCliente, c.nombres, c.apellidos,c.fechaNacimiento, t.nombre\r\n" + 
					"from cliente c inner join tipo_cliente t \r\n" + 
					"on c.idTipoCliente = t.idTpoCliente";
			
			pstm = con.prepareStatement(sql);	
			rs = pstm.executeQuery();
					
			Cliente objCliente = null;
			TipoCliente objTipoCliente = null;
			while(rs.next()){
				
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setFechaNacimiento(rs.getDate(4));
				
				objTipoCliente = new TipoCliente();
				objTipoCliente.setnombre(rs.getString(5));			

				objCliente.setIdTpoCliente(objTipoCliente);
				
				data.add(objCliente);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
