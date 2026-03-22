package com.leave.util;

import jakarta.persistence.*;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("leavePU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}