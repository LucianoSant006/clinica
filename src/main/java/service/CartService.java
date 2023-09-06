package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import entity.Cart;
import entity.Consult;

@Service
public class CartService {
	
	private List<Cart> itens = new ArrayList<>();
	
	public List<Cart> ListItens(){
		return itens;
	}
	
	public void removeIten(Consult consult) {
		itens.removeIf(itens -> itens.getConsult().equals(consult));
	}
	
	public BigDecimal calcTotal() {
		return itens.stream()
				.map(Cart::getValue)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
