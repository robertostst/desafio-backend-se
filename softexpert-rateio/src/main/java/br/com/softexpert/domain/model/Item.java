package br.com.softexpert.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@NotBlank(message = "Informe uma descrição para o item.")
	private String descricao;

	@NotNull(message = "Informe o valor do item.")
	private BigDecimal valor;

	@NotNull(message = "Informe quem pediu o item.")
	private Cliente cliente;

	public String getNomePessoa() {
		String nome = this.cliente.getNome();
		return StringUtils.hasLength(nome) ? StringUtils.trimWhitespace(nome) : "";
	}	

}
