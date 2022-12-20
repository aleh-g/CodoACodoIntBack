package data;

import static data.Conexion.close;
import static data.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hardware;
        
public class HardwareDAO {
    private static final String SQL_SELECT = "SELECT * FROM hardware";
    private static final String SQL_SELECT_BY_ID = "SELECT idHardware, nombre, categoria, precio, cantidad, marca FROM hardware WHERE idHardware = ?";
    private static final String SQL_INSERT = "INSERT INTO hardware(nombre, categoria, precio, cantidad, marca) VALUES(?, ?, ?, ? ,?)";
    private static final String SQL_UPDATE = "UPDATE hardware SET nombre = ?, categoria = ?, precio = ?, cantidad = ?, marca = ? WHERE idHardware = ?";
    private static final String SQL_DELETE = "DELETE FROM hardware WHERE idHardware = ?";
   
    
    public List<Hardware> seleccionar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Hardware hardware = null;
        List<Hardware> hardwares = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idHardware = rs.getInt(1);
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String marca = rs.getString("marca");

                hardware = new Hardware(idHardware, nombre, categoria, precio, cantidad, marca);

                hardwares.add(hardware);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return hardwares;
    }
    
    public int insertar(Hardware hardware){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, hardware.getNombre());
            stmt.setString(2, hardware.getCategoria());
            stmt.setDouble(3, hardware.getPrecio());
            stmt.setInt(4, hardware.getCantidad());
            stmt.setString(5, hardware.getMarca());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int actualizar(Hardware hardware){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, hardware.getNombre());
            stmt.setString(2, hardware.getCategoria());
            stmt.setDouble(3, hardware.getPrecio());
            stmt.setInt(4, hardware.getCantidad());
            stmt.setString(5, hardware.getMarca());
            stmt.setInt(6, hardware.getIdHardware());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
     public int eliminar(int idHardware){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, idHardware);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
     
    public Hardware seleccionarPorId(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Hardware hardware = null;

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idHardware = rs.getInt("idHardware");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String marca = rs.getString("marca");

                hardware = new Hardware(idHardware, nombre, categoria, precio, cantidad, marca);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return hardware;
    }
    
}
