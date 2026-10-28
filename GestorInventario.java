import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Sistema de Gestion de Inventario y Ventas
 * -------------------------------------------
 * Aplicacion de consola para administrar el inventario de un pequeño
 * negocio (por ejemplo, una cafeteria): registrar productos, realizar
 * ventas con descuento de stock automatico, y generar un reporte de
 * ventas con el total recaudado.
 *
 * Demuestra: programacion orientada a objetos (clases, atributos,
 * metodos, constructores), colecciones (ArrayList), y manejo de
 * entrada de usuario por consola.
 *
 * Autor: Juan Osvaldo Lopez Meneses
 */
public class GestorInventario {

    /**
     * Representa un producto del inventario.
     */
    static class Producto {
        private String nombre;
        private double precio;
        private int stock;

        public Producto(String nombre, double precio, int stock) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        public String getNombre() { return nombre; }
        public double getPrecio() { return precio; }
        public int getStock() { return stock; }

        public void reducirStock(int cantidad) {
            this.stock -= cantidad;
        }

        @Override
        public String toString() {
            return String.format("%-20s $%-8.2f Stock: %d", nombre, precio, stock);
        }
    }

    /**
     * Representa una venta realizada (un producto vendido con su cantidad).
     */
    static class Venta {
        private Producto producto;
        private int cantidad;
        private double total;

        public Venta(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
            this.total = producto.getPrecio() * cantidad;
        }

        public double getTotal() { return total; }

        @Override
        public String toString() {
            return String.format("%-20s x%-3d $%.2f", producto.getNombre(), cantidad, total);
        }
    }

    // Inventario y registro de ventas del negocio
    private List<Producto> inventario = new ArrayList<>();
    private List<Venta> ventasDelDia = new ArrayList<>();

    /**
     * Carga productos de ejemplo al iniciar el sistema.
     */
    public void cargarInventarioInicial() {
        inventario.add(new Producto("Cafe", 20.0, 50));
        inventario.add(new Producto("Agua", 15.0, 40));
        inventario.add(new Producto("Sandwich", 35.0, 20));
        inventario.add(new Producto("Jugo", 25.0, 30));
        inventario.add(new Producto("Pan de chocolate", 30.0, 25));
    }

    /**
     * Busca un producto por nombre (sin distinguir mayusculas/minusculas).
     * @return el producto encontrado, o null si no existe.
     */
    private Producto buscarProducto(String nombre) {
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void mostrarInventario() {
        System.out.println("\n--- INVENTARIO ACTUAL ---");
        for (Producto p : inventario) {
            System.out.println(p);
        }
    }

    /**
     * Registra una venta: valida que exista stock suficiente, descuenta
     * del inventario y agrega la venta al reporte del dia.
     */
    public boolean registrarVenta(String nombreProducto, int cantidad) {
        Producto producto = buscarProducto(nombreProducto);

        if (producto == null) {
            System.out.println("Error: el producto \"" + nombreProducto + "\" no existe en el inventario.");
            return false;
        }
        if (cantidad <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a cero.");
            return false;
        }
        if (producto.getStock() < cantidad) {
            System.out.println("Error: stock insuficiente de \"" + producto.getNombre() +
                    "\". Disponible: " + producto.getStock());
            return false;
        }

        producto.reducirStock(cantidad);
        Venta venta = new Venta(producto, cantidad);
        ventasDelDia.add(venta);

        System.out.printf("Venta registrada: %s%n", venta);
        return true;
    }

    public void mostrarReporteVentas() {
        System.out.println("\n--- REPORTE DE VENTAS DEL DIA ---");
        if (ventasDelDia.isEmpty()) {
            System.out.println("No se han registrado ventas.");
            return;
        }

        double totalRecaudado = 0;
        for (Venta v : ventasDelDia) {
            System.out.println(v);
            totalRecaudado += v.getTotal();
        }
        System.out.printf("TOTAL RECAUDADO: $%.2f%n", totalRecaudado);
        System.out.println("Numero de ventas: " + ventasDelDia.size());
    }

    public void agregarProducto(String nombre, double precio, int stock) {
        inventario.add(new Producto(nombre, precio, stock));
        System.out.println("Producto \"" + nombre + "\" agregado al inventario.");
    }

    /**
     * Menu interactivo por consola.
     */
    public void iniciarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========= GESTOR DE INVENTARIO Y VENTAS =========");
            System.out.println("1. Ver inventario");
            System.out.println("2. Registrar venta");
            System.out.println("3. Agregar producto nuevo");
            System.out.println("4. Ver reporte de ventas del dia");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    System.out.print("Nombre del producto: ");
                    String nombreVenta = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = leerEntero(sc);
                    registrarVenta(nombreVenta, cantidad);
                    break;
                case 3:
                    System.out.print("Nombre del nuevo producto: ");
                    String nombreNuevo = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = leerDouble(sc);
                    System.out.print("Stock inicial: ");
                    int stockInicial = leerEntero(sc);
                    agregarProducto(nombreNuevo, precio, stockInicial);
                    break;
                case 4:
                    mostrarReporteVentas();
                    break;
                case 5:
                    System.out.println("Cerrando el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (opcion != 5);

        sc.close();
    }

    private int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Por favor ingresa un numero valido.");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpiar salto de linea pendiente
        return valor;
    }

    private double leerDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor ingresa un numero valido.");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    public static void main(String[] args) {
        GestorInventario sistema = new GestorInventario();
        sistema.cargarInventarioInicial();
        sistema.iniciarMenu();
    }
}
