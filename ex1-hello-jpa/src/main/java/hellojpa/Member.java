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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
@Entity
public class Member {

  @Id @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @ManyToOne
  @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
  // insertable, updatable false -> 읽기 전용으로 만듬
  // 일대다 양방향은 공식적으로 존재X 읽기 전용 필드를 사용해야함 일대다가 아닌 다대일을 사용하자.
  private Team team;

  @OneToOne
  @JoinColumn(name = "LOCKER_ID")
  private Locker locker;

  @OneToMany
  @JoinTable(name = "member")
  private List<MemberProduct> memberProducts = new ArrayList<>();

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Locker getLocker() {
    return locker;
  }

  public void setLocker(Locker locker) {
    this.locker = locker;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
