
package com.emuldec.emuldeccrud;

import com.emuldec.emuldeccrud.config.HibernateUtil;
import org.hibernate.Session;

public class PruebaConexion {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.doWork(connection -> {
                System.out.println("¡Conexión exitosa con PostgreSQL!");
                System.out.println("Base de datos: "
                        + connection.getCatalog());
                System.out.println("Usuario: "
                        + connection.getMetaData().getUserName());
            });

        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
