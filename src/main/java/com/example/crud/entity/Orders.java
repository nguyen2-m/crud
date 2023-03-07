package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    private Date created;
    private Date modified;
    private String note;
    private int price;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_phone")
    private String receiverPhone;
    private int status;
    private int total;
    @OneToOne
    @JoinColumn(name = "buyer")
    private Users buyer;
    @OneToOne
    @JoinColumn(name = "create_by")
    private Users createBy;
    @OneToOne
    @JoinColumn(name = "modified_by")
    private Users modifiedBy;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
