public class Alimento extends Producto {

	 public Alimento(String codigo, String descripcion, double precio, int inventario) {
	        super(codigo, descripcion, precio, inventario);
	    }

	    @Override
	    String tipo() {
	        return "Alimento";
	    }
}
