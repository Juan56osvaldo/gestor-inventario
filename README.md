# Gestor de Inventario y Ventas

Aplicación de consola en Java para administrar el inventario y las ventas de un pequeño negocio (por ejemplo, una cafetería). Permite registrar productos, realizar ventas con descuento automático de stock, y generar un reporte con el total recaudado.

## ¿Qué hace?

- **Ver inventario**: muestra todos los productos disponibles con su precio y existencias.
- **Registrar una venta**: descuenta automáticamente del stock y valida que haya suficiente cantidad disponible.
- **Agregar un producto nuevo**: permite ampliar el catálogo en tiempo real.
- **Generar reporte de ventas**: muestra todas las ventas del día y el total recaudado.
- **Validación de errores**: evita vender productos inexistentes o con stock insuficiente, y valida que las cantidades ingresadas sean números válidos.

## Diseño del programa

El sistema está construido con **programación orientada a objetos**:

| Clase | Responsabilidad |
|-------|------------------|
| `Producto` | Representa un artículo del inventario (nombre, precio, stock) |
| `Venta` | Representa una venta realizada (producto, cantidad, total) |
| `GestorInventario` | Administra el inventario, procesa ventas y controla el menú interactivo |

## Cómo ejecutarlo

Requiere Java 11 o superior.

```bash
java GestorInventario.java
```

Se abre un menú interactivo en la consola:

```
========= GESTOR DE INVENTARIO Y VENTAS =========
1. Ver inventario
2. Registrar venta
3. Agregar producto nuevo
4. Ver reporte de ventas del dia
5. Salir
```

El inventario inicia con 5 productos de ejemplo (Café, Agua, Sandwich, Jugo, Pan de chocolate), listos para probar el sistema de inmediato.

## Autor

Juan Osvaldo Lopez Meneses — Estudiante de Sistemas Computacionales
