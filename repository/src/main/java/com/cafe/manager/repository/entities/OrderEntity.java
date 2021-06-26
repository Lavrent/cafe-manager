package com.cafe.manager.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cafe_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_table_id")
    private TableEntity table;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<ProductInOrderEntity> productInOrderEntities = new HashSet<>();
}
