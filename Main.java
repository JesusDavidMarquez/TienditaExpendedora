import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		 MaquinaExpendedora maquina = new MaquinaExpendedora();
		  
		  
		    Scanner scanner = new Scanner(System.in);
		    int opcion;

		    do {
		        System.out.println("\n--- Máquina Expendedora ---");
		        System.out.println("1. Mostrar productos disponibles");
		        System.out.println("2. Agregar productos al contenedor");
		        System.out.println("3. Agregar productos al carrito");
		        System.out.println("4. Finalizar compra");
		        System.out.println("5. Mostrar ventas del día");
		        System.out.println("6. Salir");
		        System.out.print("Seleccione una opción: ");
		        opcion = scanner.nextInt();

		        switch (opcion) {
		            case 1:
		                maquina.mostrarProductos();
		                break;
		            case 2:
		                System.out.print("Ingrese el código del producto: ");
		                String codigo = scanner.next();
		                System.out.print("Ingrese la cantidad a agregar: ");
		                int cantidad = scanner.nextInt();
		                maquina.agregarProducto(codigo, cantidad);
		                break;
		            case 3:
		                System.out.print("Ingrese el código del producto a agregar al carrito: ");
		                String codigoProducto = scanner.next();
		                Producto producto = null;
		                for (Producto p : maquina.productos) {
		                    if (p.codigo.equals(codigoProducto)) {
		                        producto = p;
		                        break;
		                    }
		                }
		                if (producto != null) {
		                    maquina.agregarProducto(producto);
		                } else {
		                    System.out.println("Producto no encontrado.");
		                }
		                break;
		            case 4:
		                maquina.finalizarCompra();
		                break;
		            case 5:
		                maquina.mostrarVentasDelDia();
		                break;
		            case 6:
		                System.out.println("Saliendo...");
		                break;
		            default:
		                System.out.println("Opción no válida.");
		        }
		    } while (opcion != 6);
		    scanner.close();	    
		   
		   
		

	}
}






