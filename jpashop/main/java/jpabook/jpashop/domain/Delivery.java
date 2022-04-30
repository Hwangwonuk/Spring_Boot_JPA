package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // STRING을 써야 READY, COMP사이에 다른상태값이 생겨도 오류가 생기지 않음 ENUM시 꼭 확인
    private DeliveryStatus status; // READY, COMP 준비, 배송
}
