import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.IOException;

public class MaquinaExpendedora implements Carrito {

		ArrayList<Producto> carrito = new ArrayList<>();
	 	ArrayList<Producto> productos = new ArrayList<>();
	    ArrayList<String> ventasDelDia = new ArrayList<>();
	    double total = 0;

	    public MaquinaExpendedora() {
	        productos.add(new Alimento("1", "Gansitos", 10.5, 10));
	        productos.add(new Alimento("2", "Ruffles", 8.75, 10));
	        productos.add(new Bebida("3", "Coca-Cola", 15, 20));
	        productos.add(new Bebida("4", "Pepsi", 14, 20));
			productos.add(new Bebida("5", "Dr. Pepper", 18, 20));
			productos.add(new Alimento("6", "Cheetos", 10, 10));
	    }

		public void mostrarProductos() {
			System.out.println("Productos disponibles:");
			System.out.printf("%-10s %-20s %-10s %-10s %-10s\n", "Código", "Descripción", "Precio", "Tipo", "Inventario");
			System.out.println("---------------------------------------------------------------------");
			for (Producto p : productos) {
				System.out.printf("%-10s %-20s %-10.2f %-10s %-10d\n", 
								  p.codigo, p.descripcion, p.precio, p.tipo(), p.inventario);
			}
		}

		public void agregarProducto(String codigo, int cantidad) {
			try {
				for (Producto p : productos) {
					if (p.codigo.equals(codigo)) {
						if (p instanceof Alimento && p.inventario + cantidad > 10) {
							System.out.println("El contenedor de alimentos está lleno.");
							return;
						} else if (p instanceof Bebida && p.inventario + cantidad > 20) {
							System.out.println("El contenedor de bebidas está lleno.");
							return;
						}
						p.inventario += cantidad;
						System.out.println("Se han agregado " + cantidad + " unidades de " + p.descripcion + " al inventario.");
						return;
					}
				}
				System.out.println("Código de producto no encontrado.");
			} catch (Exception e) {
				System.out.println("Error: Entrada inválida. Intente de nuevo.");
			}
		}
		
		public void agregarProducto(Producto producto) {
			if (producto.inventario > 0) {
				total += producto.precio;
				producto.inventario--;
				carrito.add(producto);
				System.out.println("Se ha agregado " + producto.descripcion + " al carrito.");
			} else {
				System.out.println("No hay inventario disponible para " + producto.descripcion);
			}
		}
		
		public void finalizarCompra() {
			if (total == 0) {
				System.out.println("No hay productos en el carrito.");
				return;
			}
		
			System.out.println("\nTotal a pagar: " + total);
			Scanner scanner = new Scanner(System.in);
			try {
				System.out.print("Ingrese la cantidad con la que paga: ");
				double pago = scanner.nextDouble();
				if (pago < total) {
					System.out.println("El pago es insuficiente.");
				} else {
					double cambio = pago - total;
					System.out.println("Cambio a devolver: " + cambio);
					generarComprobante(); 
					registrarVenta();     
					carrito.clear();      
					total = 0;
					System.out.println("Gracias por su compra.");          
				}
			} catch (Exception e) {
				System.out.println("Error: Entrada inválida. Intente de nuevo.");
			}
		}
		
		private void generarComprobante() {
			try (FileWriter writer = new FileWriter("comprobante.txt")) {
				writer.write("Productos comprados:\n");
				for (Producto p : carrito) {
					writer.write(p.descripcion + "\n");
				}
				writer.write("Total: " + total + "\n");
				System.out.println("Se ha generado el comprobante en comprobante.txt.");
			} catch (IOException e) {
				System.out.println("Error al generar el comprobante.");
			}
		}
		
		private void registrarVenta() {
			Date fechaHoraActual = new Date();
			SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String fechaHora = formatoFechaHora.format(fechaHoraActual);
		
			StringBuilder venta = new StringBuilder();
			venta.append("Fecha y hora: ").append(fechaHora).append("\n");
			venta.append("Productos comprados:\n");
			for (Producto p : carrito) {
				venta.append(p.descripcion).append("\n");
			}
			venta.append("Total: ").append(total).append("\n");
		
			ventasDelDia.add(venta.toString());
		
			try (FileWriter writer = new FileWriter("ventas_del_dia.txt", true)) {
				writer.write(venta.toString());
				writer.write("\n----------------------\n");
				System.out.println("Venta registrada en el archivo de ventas del día.");
			} catch (IOException e) {
				System.out.println("Error al registrar la venta en el archivo.");
			}
		}

	    public void mostrarVentasDelDia() {

	        if (ventasDelDia.isEmpty()) {
	            System.out.println("No hay ventas registradas hoy.");
	            return;
	        }

	        System.out.println("Ventas del día:");
	        for (String venta : ventasDelDia) {
	            System.out.println(venta);
	        }
	    }  
}
