public abstract class Producto {
	
	    public String codigo;
	    public String descripcion;
	    public double precio;
	    public  int inventario;

	    public Producto(String codigo, String descripcion, double precio, int inventario) {
	        this.codigo = codigo;
	        this.descripcion = descripcion;
	        this.precio = precio;
	        this.inventario = inventario;
	    }

	    abstract String tipo();

}
