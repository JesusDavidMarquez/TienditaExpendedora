public class Bebida extends Producto{

	 public Bebida(String codigo, String descripcion, double precio, int inventario) {
	        super(codigo, descripcion, precio, inventario);
	    }

	    @Override
	    String tipo() {
	        return "Bebida";
	    }
}
