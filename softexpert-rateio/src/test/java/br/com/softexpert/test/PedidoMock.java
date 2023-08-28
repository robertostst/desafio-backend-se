package br.com.softexpert.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.softexpert.domain.model.Cliente;
import br.com.softexpert.domain.model.Item;
import br.com.softexpert.domain.model.Pedido;

public final class PedidoMock {

	public static Pedido get() {		
		Pedido pedido = Pedido.builder()
			.itens(new ArrayList<>())
			.valorDesconto(BigDecimal.valueOf(20))
			.valorTaxaEntrega(BigDecimal.valueOf(8))			
			.build();
		
		Cliente cliente1 = Cliente.builder()
				.id(1L)
				.nome("Roberto")
				.build();
		
		Cliente cliente2 = Cliente.builder()
				.id(2L)
				.nome("Amigo")
				.build();
		
		Item item1 = Item.builder()
				.cliente(cliente1)
				.descricao("Hamburger")
				.valor(BigDecimal.valueOf(40))
				.build();
		
		Item item2 = Item.builder()
				.cliente(cliente1)
				.descricao("Sobremesa")
				.valor(BigDecimal.valueOf(2))
				.build();
		
		Item item3 = Item.builder()
				.cliente(cliente2)
				.descricao("Sanduiche (Amigo)")
				.valor(BigDecimal.valueOf(8))
				.build();
		
		pedido.getItens().add(item1);
		pedido.getItens().add(item2);
		pedido.getItens().add(item3);
		
		return pedido;
	}
}
