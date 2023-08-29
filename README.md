# desafio-backend-se
### SoftExpert
A ideia deste desafio é resolver um problema comum no dia-a-dia de quem divide almoços/lanches com a equipe de trabalho. Vamos imaginar que você e mais um colega queiram dividir um lanche que estão pedindo pelo iFood ou outro aplicativo em seu smartphone. Fica fácil descobrir quanto seu colega deverá lhe pagar quando não existe nenhum desconto ou valor de entrega, porém quando estes fatores entram em questão, simplesmente dividir o valor no meio pode não ser o mais justo. 

Com o objetivo de facilitar esta conta, você deverá criar uma pequena aplicação que irá calcular o total que seus amigos deverão lhe pagar quando dividirem um almoço. Ao final, você deverá simplificar o pagamento gerando um link do Picpay (ou outra carteira de sua preferência), para enviar a cobrança a seus amigos. 

#### Swagger
http://localhost:8090/api/rateio/swagger-ui.html#

![image](https://github.com/robertostst/desafio-backend-se/blob/main/assets/captura_swagger.png)


#### URL do serviço (com parâmetro para geração de links de pagamento)
POST localhost:8090/api/rateio/calculos?gerarLinkPagamento=true

#### JSON de Entrada (exemplo)
```
{
  "pedidoParaRateio": {
    "itens": [
      {
        "descricao": "Hamburger",
        "valor": 31.90,
        "cliente": {
            "id": 1,
            "nome": "Roberto"
        }
      },
	  {
        "descricao": "Sobremesa",
        "valor": 8,
        "cliente": {
            "id": 1,
            "nome": "Roberto"
        }
      },
	  {
        "descricao": "Sanduiche(Amigo)",
        "valor": 12,
        "cliente": {
            "id": 2,
            "nome": "Amigo"
        }
      }
    ],
    "valorTaxaEntrega": 4.00,
    "valorDesconto": 5.55
  }
  ,
  "chave": "(11)963222622"
}
```

#### Resultado (Exemplo de operação bem sucedida)
```
{
    "rateio": {
        "valorTotalAPagar": 50.35,
        "valorAPagarPorPessoa": {
            "Roberto": 38.72,
            "Amigo": 11.63
        },
        "linksPagamento": [
            {
                "identificacaoDevedor": "Roberto",
                "valorAPagar": 38.72,
                "pixLink": "pix.example.com/qr/v2/9d36b84fc70b478fb95c12729b90ca25",
                "qrcode": null
            },
            {
                "identificacaoDevedor": "Amigo",
                "valorAPagar": 11.63,
                "pixLink": "pix.example.com/qr/v2/9d36b84fc70b478fb95c12729b90ca25",
                "qrcode": null
            }
        ]
    }
}
```

#### Foi utilizada a API do Banco Itaú para gerar o link de pagamento
https://devportal.itau.com.br/nossas-apis/itau-ep9-gtw-pix-recebimentos-conciliacoes-v2-ext#operation/post/cobrancas_imediata_pix
