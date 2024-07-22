package entity;

public class Cliente {
    private int idCliente;
    private String nomCliente;
    private String ape1Cliente;
    private String ape2Cliente;
    private String rfcCliente;
    private String generoCliente;
    private String correoCliente;
    private String telCliente;

    public Cliente() {
    }

    public Cliente(int idCliente, String nomCliente, String ape1Cliente, String ape2Cliente, String rfcCliente, String generoCliente, String correoCliente, String telCliente) {
        this.idCliente = idCliente;
        this.nomCliente = nomCliente;
        this.ape1Cliente = ape1Cliente;
        this.ape2Cliente = ape2Cliente;
        this.rfcCliente = rfcCliente;
        this.generoCliente = generoCliente;
        this.correoCliente = correoCliente;
        this.telCliente = telCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getApe1Cliente() {
        return ape1Cliente;
    }

    public void setApe1Cliente(String ape1Cliente) {
        this.ape1Cliente = ape1Cliente;
    }

    public String getApe2Cliente() {
        return ape2Cliente;
    }

    public void setApe2Cliente(String ape2Cliente) {
        this.ape2Cliente = ape2Cliente;
    }

    public String getRfcCliente() {
        return rfcCliente;
    }

    public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    public String getGeneroCliente() {
        return generoCliente;
    }

    public void setGeneroCliente(String generoCliente) {
        this.generoCliente = generoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nomCliente=" + nomCliente + ", ape1Cliente=" + ape1Cliente + ", ape2Cliente=" + ape2Cliente + ", rfcCliente=" + rfcCliente + ", generoCliente=" + generoCliente + ", correoCliente=" + correoCliente + ", telCliente=" + telCliente + '}';
    }
}
