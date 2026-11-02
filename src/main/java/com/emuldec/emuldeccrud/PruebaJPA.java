
package com.emuldec.emuldeccrud;

import com.emuldec.emuldeccrud.config.HibernateUtil;
import com.emuldec.emuldeccrud.model.Producto;
import org.hibernate.Session;
import java.util.List;

public class PruebaJPA {

    public static void main(String[] args) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            // 1. CONSULTAR TODOS LOS PRODUCTOS
            System.out.println("\n=== PRODUCTOS DE EMULDEC ===");

            List<Producto> productos = session.createQuery(
                    "SELECT p FROM Producto p",
                    Producto.class)
                    .getResultList();

            for (Producto p : productos) {
                System.out.println(
                        "ID: " + p.getId()
                        + " | Nombre: " + p.getNombre()
                        + " | Precio: $" + p.getPrecio()
                        + " | Cantidad: " + p.getCantidad()
                );
            }

            // 2. CONSULTAR PRODUCTOS POR CATEGORIA
            System.out.println("\n=== PRODUCTOS POR CATEGORIA ===");

            List<Producto> productosCategoria = session.createQuery(
                    "SELECT p FROM Producto p "
                    + "WHERE p.categoria.nombre = :nombre",
                    Producto.class)
                    .setParameter("nombre", "Emulsiones asfalticas")
                    .getResultList();

            for (Producto p : productosCategoria) {
                System.out.println(
                        "Producto: " + p.getNombre()
                        + " | Precio: $" + p.getPrecio()
                );
            }

            // 3. CONSULTAR PRODUCTOS POR PRECIO
            System.out.println("\n=== PRODUCTOS CON PRECIO MAYOR A $40 ===");

            List<Producto> productosPrecio = session.createQuery(
                    "SELECT p FROM Producto p "
                    + "WHERE p.precio > :precio",
                    Producto.class)
                    .setParameter("precio", 40.0)
                    .getResultList();

            for (Producto p : productosPrecio) {
                System.out.println(
                        "Producto: " + p.getNombre()
                        + " | Precio: $" + p.getPrecio()
                );
            }

            // 4. CONSULTAR PRODUCTOS POR PROVEEDOR
            System.out.println("\n=== PRODUCTOS POR PROVEEDOR ===");

            List<Producto> productosProveedor = session.createQuery(
                    "SELECT p FROM Producto p "
                    + "WHERE p.proveedor.nombre = :proveedor",
                    Producto.class)
                    .setParameter("proveedor", "Proveedor EMULDEC")
                    .getResultList();

            for (Producto p : productosProveedor) {
                System.out.println(
                        "Producto: " + p.getNombre()
                        + " | Precio: $" + p.getPrecio()
                );
            }

            System.out.println("\n=== CONSULTAS JPQL FINALIZADAS ===");

        } catch (Exception e) {
            System.err.println("Error en las consultas JPQL:");
            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
