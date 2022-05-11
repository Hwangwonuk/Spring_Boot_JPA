/*
 * Created by Wonuk Hwang on 2022/05/11
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by infra Team <wonuk_hwang@bigin.io>, 2022/05/11
 */
package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * create on 2022/05/11. create by IntelliJ IDEA.
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
public class Locker {

  @Id @GeneratedValue
  private long id;

  private String name;

  @OneToOne(mappedBy = "locker")
  private Member member;
}
