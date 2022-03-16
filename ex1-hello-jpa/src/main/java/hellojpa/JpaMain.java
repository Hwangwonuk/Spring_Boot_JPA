/*
 * Created by Wonuk Hwang on 2022/03/08
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by infra Team <wonuk_hwang@bigin.io>, 2022/03/08
 */
package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.HibernateException;

/**
 * create on 2022/03/08. create by IntelliJ IDEA.
 *
 * <p> </p>
 * <p> {@link } and {@link } </p> *
 *
 * @author wonukHwang
 * @version 1.0
 * @see
 * @since (ex : 5 + 5)
 */
public class JpaMain {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member member1 = new Member();
      member1.setUsername("A");

      Member member2 = new Member();
      member2.setUsername("B");

      Member member3 = new Member();
      member3.setUsername("C");

      System.out.println("==========");

      // DB SEQ = 1    |    1
      // DB SEQ = 51   |    2
      // DQ SEQ = 52   |    3

      em.persist(member1); // 1, 51
      em.persist(member2); // MEM
      em.persist(member3); // MEM

      System.out.println("member1 = " + member1.getId());
      System.out.println("member2 = " + member2.getId());
      System.out.println("member3 = " + member3.getId());

      System.out.println("==========");

      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

}
