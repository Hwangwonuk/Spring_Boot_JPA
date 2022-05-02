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

      // 저장
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("member1");
      em.persist(member);

      team.addMember(member);

      em.flush();
      em.clear();

      Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
      List<Member> members = findTeam.getMembers();
// Entity는 Controller에서 반환하지 않아야 많은 문제가 해결된다 dto를 사용해야함
      System.out.println("=========");
//      System.out.println("members = " + findTeam); 무한루프
      for (Member m : members) {
        System.out.println("m = " + m.getUsername());
      }
      System.out.println("=========");

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
