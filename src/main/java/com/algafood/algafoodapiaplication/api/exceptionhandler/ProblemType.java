package com.algafood.algafoodapiaplication.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "RECURSO NÃO ENCONTRADO"),
    ENTIDADE_EM_USO("/entidade-em-uso", "ENTIDADE EM USO"),
    ERRO_NEGOCIO("/erro-negocio", "VIOLAÇÃO DA REGRA DE NEGOCIO"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incopreensivel", "MENSAGEM INCONPREENSIVEL"),
    PARAMETRO_INVALIDO("/parametro-invalido", "PARAMETRO INFORMADO INVALIDO");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br/" + path;
        this.title = title;
    }


}
