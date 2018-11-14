package com.noelmace.gestionclient.spring.jsf.backing;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.noelmace.gestionclient.spring.business.dto.BonDeCommandeTO;
import com.noelmace.gestionclient.spring.business.services.OrderingService;

public class BdcBacking {

	private OrderingService orderingService;

	private String ref;

	private double price;

	private long clientId;

	private List<BonDeCommandeTO> bdcList;

	private Long id;

	@PostConstruct
	public void init() {
		try {
			this.bdcList = orderingService.list();
			System.out.println(bdcList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add() throws SQLException{
		this.id = orderingService.makeOrder(clientId, ref, price);
		init();
	}

	public void delete(BonDeCommandeTO bdc) throws SQLException {
		orderingService.delete(bdc.getId());
		init();
	}

	public void update() throws SQLException {
		orderingService.update(this.id, this.ref, this.price);
		init();
	}

	// actions

	public String initModify(BonDeCommandeTO bdc){
		this.ref = bdc.getRef();
		this.price = bdc.getPrice();
		this.id = bdc.getId();
		return "/bdc/modify";
	}

	// get et setters


	public void setOrderingService(OrderingService orderingService) {
		this.orderingService = orderingService;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getClientId() {
		return clientId;
	}

	public List<BonDeCommandeTO> getBdcList() {
		return bdcList;
	}


}
