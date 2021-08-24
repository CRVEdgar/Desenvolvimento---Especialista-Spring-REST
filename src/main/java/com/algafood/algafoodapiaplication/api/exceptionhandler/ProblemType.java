package com.algafood.algafoodapiaplication.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "ENTIDADE NÃO ENCONTRADA");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br/" + path;
        this.title = title;
    }
}
