
package com.emuldec.emuldeccrud;

import com.emuldec.emuldeccrud.config.HibernateUtil;
import com.emuldec.emuldeccrud.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductoDAO {

    public void guardar(Producto producto) {
        Transaction transaccion = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaccion = session.beginTransaction();

            session.persist(producto);

            transaccion.commit();

            System.out.println("Producto guardado correctamente.");
        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            System.err.println("Error al guardar el producto:");
            e.printStackTrace();
        }
  }

    public java.util.List<Producto> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Producto", Producto.class
            ).list();
        } catch (Exception e) {
            System.err.println("Error al listar los productos:");
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    public Producto buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Producto.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar el producto:");
            e.printStackTrace();
            return null;
        }
    }
    
    public void actualizar(Producto producto) {
        Transaction transaccion = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaccion = session.beginTransaction();

            session.merge(producto);

            transaccion.commit();

            System.out.println("Producto actualizado correctamente.");
        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            System.err.println("Error al actualizar el producto:");
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        Transaction transaccion = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaccion = session.beginTransaction();

            Producto producto = session.get(Producto.class, id);

            if (producto != null) {
                session.remove(producto);
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró el producto con ID: " + id);
            }

            transaccion.commit();
        } catch (Exception e) {
            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            System.err.println("Error al eliminar el producto:");
            e.printStackTrace();
        }
    }

}

