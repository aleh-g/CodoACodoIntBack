package model;

public class Hardware {
    private int idHardware;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidad;
    private String marca;

    public Hardware() {
    }

    public Hardware(String nombre, String categoria, double precio, int cantidad, String marca) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.marca = marca;
    }
    
    
    public Hardware(int idHardware, String nombre, String categoria, double precio, int cantidad, String marca) {
        this.idHardware = idHardware;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.marca = marca;
    }

    public int getIdHardware() {
        return idHardware;
    }

    public void setIdHardware(int idHardware) {
        this.idHardware = idHardware;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @Override
    public String toString() {
        return "Hardware{" + "idHardware=" + idHardware + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio + ", cantidad=" + cantidad + ", marca" + marca + '}';
    }
            
}
