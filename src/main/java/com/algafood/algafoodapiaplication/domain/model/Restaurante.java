package com.algafood.algafoodapiaplication.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_restaurante")
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) // [não] aceita valores nulos
    private String nome;


    @Column(name = "taxa_frete", nullable=false)
    private BigDecimal taxaFrete;

    private Boolean ativo;
    private Boolean aberto;
    private Instant dataCadastro;
    private Instant dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable=false)
    private Cozinha cozinha;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public BigDecimal getTaxaFrete() {
//        return taxaFrete;
//    }
//
//    public void setTaxaFrete(BigDecimal taxaFrete) {
//        this.taxaFrete = taxaFrete;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Restaurante)) return false;
//
//        Restaurante that = (Restaurante) o;
//
//        return id != null ? id.equals(that.id) : that.id == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return id != null ? id.hashCode() : 0;
//    }
}
