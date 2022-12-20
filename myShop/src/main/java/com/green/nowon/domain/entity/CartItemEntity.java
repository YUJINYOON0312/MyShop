package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	private int count;//수량
	
	@JoinColumn //fk:cart_no
	@ManyToOne
	private CartEntity cart;
	
	@JoinColumn//fk:item_no
	@ManyToOne
	private ItemEntity item;
}
