package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequisicaoNovoPedido {

    //o nome dos atributos precisa ser o mesmo do nome dos inputs no formulario html

    @NotBlank //validação
    private String nomeProduto;
    @NotBlank
    private String urlProduto;
    @NotBlank
    private String urlImagem;

    private String descricao;

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setDescricao(descricao);
        pedido.setUrlProduto(urlProduto);
        pedido.setUrlImagem(urlImagem);
        pedido.setNomeProduto(nomeProduto);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        return pedido;
    }
}
