package entity;

public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String ape1Usuario;
    private String ape2Usuario;
    private String correo;
    private String usuario;
    private String clave;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombre, String ape1Usuario, String ape2Usuario, String correo, String usuario, String clave) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.ape1Usuario = ape1Usuario;
        this.ape2Usuario = ape2Usuario;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = clave;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1Usuario() {
        return ape1Usuario;
    }

    public void setApe1Usuario(String ape1Usuario) {
        this.ape1Usuario = ape1Usuario;
    }

    public String getApe2Usuario() {
        return ape2Usuario;
    }

    public void setApe2Usuario(String ape2Usuario) {
        this.ape2Usuario = ape2Usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", ape1Usuario=" + ape1Usuario + ", ape2Usuario=" + ape2Usuario + ", correo=" + correo + ", usuario=" + usuario + ", clave=" + clave + '}';
    }

}
