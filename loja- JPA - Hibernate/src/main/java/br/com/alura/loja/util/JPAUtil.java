package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja"); //o parametro é o name do persistence-unit, no arquivo xml;

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
