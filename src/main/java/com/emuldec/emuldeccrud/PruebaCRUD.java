
package com.emuldec.emuldeccrud;

import com.emuldec.emuldeccrud.model.Producto;

public class PruebaCRUD {

    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAO();

        Producto producto = new Producto(
                "Laptop",
                850.00,
                5
        );

        dao.guardar(producto);

        System.out.println("\n--- LISTA DE PRODUCTOS ---");

        for (Producto p : dao.listarTodos()) {
            System.out.println(
                    "ID: " + p.getId()
                    + " | Nombre: " + p.getNombre()
                    + " | Precio: " + p.getPrecio()
                    + " | Cantidad: " + p.getCantidad()
            );
        }

        System.out.println("\n--- BUSCAR PRODUCTO ---");

        Producto encontrado = dao.buscarPorId(1L);

        if (encontrado != null) {
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Precio: " + encontrado.getPrecio());
            System.out.println("Cantidad: " + encontrado.getCantidad());
        } else {
            System.out.println("Producto no encontrado.");
        }

        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");

        Producto productoActualizar = dao.buscarPorId(1L);

        if (productoActualizar != null) {
            productoActualizar.setPrecio(900.00);
            productoActualizar.setCantidad(8);

            dao.actualizar(productoActualizar);

            System.out.println("Nuevo precio: " + productoActualizar.getPrecio());
            System.out.println("Nueva cantidad: " + productoActualizar.getCantidad());
        } else {
            System.out.println("Producto no encontrado.");
        }

        System.out.println("\n--- ELIMINAR PRODUCTO ---");

        dao.eliminar(1L);

        System.out.println("\n--- COMPROBAR ELIMINACIÓN ---");

        Producto eliminado = dao.buscarPorId(1L);

        if (eliminado == null) {
            System.out.println("El producto fue eliminado correctamente.");
        } else {
            System.out.println("El producto todavía existe.");
        }

        System.out.println("ID del producto: " + producto.getId());
    }
}
