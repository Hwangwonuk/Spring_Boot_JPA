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

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
//      Member member = new Member();
//      member.setId(2L);
//      member.setName("HelloA");
//      em.persist(member);

//      Member findMember = em.find(Member.class, 1L);
//      List<Member> result = em.createQuery("select  m from Member as m", Member.class)
//          .setFirstResult(5)
//          .setMaxResults(8)
//          .getResultList();
//
//      for (Member member : result) {
//        System.out.println("member.getName() = " + member.getName());
//      }
      // 비영속
      Member member = new Member();
      member.setId(100L);
      member.setName("HelloJPA");

      // 영속
      System.out.println("=== BEFORE ===");
      em.persist(member);
      System.out.println("=== AFTER ===");


      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

}
