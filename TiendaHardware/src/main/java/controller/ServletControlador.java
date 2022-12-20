package controller;

import data.HardwareDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Hardware;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String accionJava = req.getParameter("accion");
        if(accionJava!=null){
            switch (accionJava) {
                case "editar":
                    editarProducto(req, res);
                    break;
                case "eliminar":
                    eliminarProducto(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    private int calcularTotalProductos(List<Hardware> hardwares){
        int total = 0;
        for(int i=0; i<hardwares.size(); i++){
            total += hardwares.get(i).getCantidad();
        }
        return total;
    }
    
    private double calcularTotalAPagar(List<Hardware> hardwares){
        double total = 0;
        for(int i=0; i<hardwares.size(); i++){
            total += hardwares.get(i).getPrecio() * hardwares.get(i).getCantidad();
        }
        return total;
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String accionJava = req.getParameter("accion");
        if(accionJava!=null){
            switch (accionJava) {
                case "insertar":
                    insertarProducto(req, res);
                    break;
                case "modificar":
                    modificarHardware(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    private void insertarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String marca = req.getParameter("marca");
        String categoria = req.getParameter("categoria");
        int cantidad = 0;
        double precio = 0;
        String cantidadString = req.getParameter("cantidad");
        if(cantidadString!=null && !cantidadString.equals("")){
            cantidad = Integer.parseInt(cantidadString);
        }
        String precioString = req.getParameter("precio");
        if(precioString!=null && !precioString.equals("")){
            precio = Double.parseDouble(precioString);
        }
        
        Hardware hardware = new Hardware(nombre, categoria, precio, cantidad, marca);
        
        int regMod = new HardwareDAO().insertar(hardware);
        
        System.out.println("Registro guardados: " + regMod);
        
        accionDefault(req, res);
    }
    
    private void accionDefault(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        List<Hardware> hardwares = new HardwareDAO().seleccionar();
        hardwares.forEach(System.out::println);
        
        HttpSession sesion = req.getSession();
        
        sesion.setAttribute("hardwares", hardwares);
        sesion.setAttribute("cantProductos", calcularTotalProductos(hardwares));
        sesion.setAttribute("totalProductos", calcularTotalAPagar(hardwares));
        res.sendRedirect("hardwares.jsp");
    }
    
    private void editarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int idHardware = Integer.parseInt(req.getParameter("idHardware"));
        
        Hardware hardware = new HardwareDAO().seleccionarPorId(idHardware);
        
        req.setAttribute("hardware", hardware);
        String jspEditar = "/WEB-INF/pages/functions/editarProducto.jsp";
        
        req.getRequestDispatcher(jspEditar).forward(req, res);
    }
    
    private void modificarHardware(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int idHardware = Integer.parseInt(req.getParameter("idHardware"));
        String nombre = req.getParameter("nombre");
        String marca = req.getParameter("marca");
        String categoria = req.getParameter("categoria");
        int cantidad = 0;
        double precio = 0;
        String cantidadString = req.getParameter("cantidad");
        if(cantidadString!=null && !cantidadString.equals("")){
            cantidad = Integer.parseInt(cantidadString);
        }
        String precioString = req.getParameter("precio");
        if(precioString!=null && !precioString.equals("")){
            precio = Double.parseDouble(precioString);
        }
        
        Hardware hardware = new Hardware(idHardware, nombre, categoria, precio, cantidad, marca);
        
        int regMod = new HardwareDAO().actualizar(hardware);
        
        System.out.println("Registro actualizados: " + regMod);
        
        accionDefault(req, res);
    }
    
    private void eliminarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int idHardware = Integer.parseInt(req.getParameter("idHardware"));
        
        int regMod = new HardwareDAO().eliminar(idHardware);
        
        System.out.println("Registro eliminados: " + regMod);
        
        accionDefault(req, res);
    }
}
