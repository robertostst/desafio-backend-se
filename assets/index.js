function calcular(event, form) {
    event.preventDefault();

    // dados a serem enviados pela solicitação POST
    let _data = {
        "pedidoParaRateio": {
            "itens": [
              {
                "descricao": form.descricao_1.value,
                "valor": form.valor_1.value,
                "cliente": {
                    "nome": form.cliente_1.value
                }
              },
              {
                "descricao": form.descricao_2.value,
                "valor": form.valor_2.value,
                "cliente": {
                    "nome": form.cliente_2.value
                }
              },
              {
                "descricao": form.descricao_3.value,
                "valor": form.valor_3.value,
                "cliente": {
                    "nome": form.cliente_3.value
                }
              }
            ],
            "valorTaxaEntrega": form.taxa_entrega.value,
            "valorDesconto": form.valor_desconto.value
          }
          ,
          "chave": form.chave.value
    }
    
    fetch('http://localhost:8090/api/rateio/calculos?gerarLinkPagamento=true', {
        method: "POST",
        body: JSON.stringify(_data),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.json()) 
    .then(json => mostrarResposta(json))
    .catch(err => console.log(err));
}

function mostrarResposta(dados) {
    const lista = dados.rateio.linksPagamento;
    let mensagem = ``;

    for (i in lista){
        mensagem += `
            Pessoa: ${lista[i].identificacaoDevedor}
            Valor a pagar: ${lista[i].valorAPagar}
            Link Pagamento: ${lista[i].pixLink}

        `;
    }

    alert(mensagem);
}
