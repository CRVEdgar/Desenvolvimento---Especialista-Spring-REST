package com.algafood.algafoodapiaplication.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "ENTIDADE NÃO ENCONTRADA"),
    ENTIDADE_EM_USO("/entidade-em-uso", "ENTIDADE EM USO"),
    ERRO_NEGOCIO("/erro-negocio", "VIOLAÇÃO DA REGRA DE NEGOCIO"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incopreensivel", "MENSAGEM INCONPREENSIVEL");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br/" + path;
        this.title = title;
    }


}