package com.emuldec.emuldeccrud.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.emuldec.emuldeccrud.model.Producto;
import com.emuldec.emuldeccrud.model.Categoria;
import com.emuldec.emuldeccrud.model.Proveedor;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
            buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Producto.class)
                    .addAnnotatedClass(Categoria.class)
                    .addAnnotatedClass(Proveedor.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Error al inicializar Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}