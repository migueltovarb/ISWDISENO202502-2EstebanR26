import java.util.Scanner;

public class inventarioSupermercado {
    public static final int MAX_PRODUCTOS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nombres = new String[MAX_PRODUCTOS];
        int[] cantidades = new int[MAX_PRODUCTOS];

        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            System.out.print("Ingrese el nombre del producto " + (i + 1) + ": ");
            nombres[i] = scanner.nextLine();

            int cantidad;
            do {
                System.out.print("Ingrese la cantidad de " + nombres[i] + ": ");
                cantidad = scanner.nextInt();
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa. Intente de nuevo.");
                }
            } while (cantidad < 0);
            cantidades[i] = cantidad;
            scanner.nextLine();
        }

        int opcion;
        do {
            System.out.println("\n--- Menú Inventario Supermercado ---");
            System.out.println("1. Mostrar todos los productos y existencias");
            System.out.println("2. Buscar un producto por nombre");
            System.out.println("3. Actualizar inventario");
            System.out.println("4. Mostrar alertas de bajo stock");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    int total = 0;
                    System.out.println("\nInventario:");
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        System.out.println(nombres[i] + " - " + cantidades[i]);
                        total += cantidades[i];
                    }
                    System.out.println("Total de productos en inventario: " + total);
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del producto a buscar: ");
                    String buscar = scanner.nextLine();
                    boolean encontrado = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (nombres[i].equalsIgnoreCase(buscar)) {
                            System.out.println(nombres[i] + " - " + cantidades[i]);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nombre del producto a actualizar: ");
                    String actualizar = scanner.nextLine();
                    boolean actualizado = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (nombres[i].equalsIgnoreCase(actualizar)) {
                            System.out.print("Ingrese el cambio en cantidad (+ o -): ");
                            int cambio = scanner.nextInt();
                            if (cantidades[i] + cambio >= 0) {
                                cantidades[i] += cambio;
                                System.out.println("Actualizado: " + nombres[i] + " - " + cantidades[i]);
                            } else {
                                System.out.println("Error: la cantidad no puede quedar negativa.");
                            }
                            actualizado = true;
                            scanner.nextLine();
                            break;
                        }
                    }
                    if (!actualizado) {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Productos con bajo stock (menos de 10):");
                    boolean bajoStock = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (cantidades[i] < 10) {
                            System.out.println(nombres[i] + " - " + cantidades[i]);
                            bajoStock = true;
                        }
                    }
                    if (!bajoStock) {
                        System.out.println("No hay productos con bajo stock.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}

