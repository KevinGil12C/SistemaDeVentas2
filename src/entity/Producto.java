package entity;

public class Producto {
    private int idProducto;
    private String nomProducto;
    private int idCategoria;
    private String tam;
    private float precio;
    private int stock;

    public Producto() {
    }

    public Producto(int idProducto, String nomProducto, int idCategoria, String tam, float precio, int stock) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.idCategoria = idCategoria;
        this.tam = tam;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int idProducto, String nomProducto, float precio, int stock) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nomProducto=" + nomProducto + ", idCategoria=" + idCategoria + ", tam=" + tam + ", precio=" + precio + ", stock=" + stock + '}';
    }
}
