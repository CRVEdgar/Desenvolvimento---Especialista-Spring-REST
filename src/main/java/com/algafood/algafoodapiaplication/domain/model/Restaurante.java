package com.algafood.algafoodapiaplication.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_restaurante")
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false) // [não] aceita valores nulos
    private String nome;


    @Column(name = "taxa_frete", nullable=false)
    private BigDecimal taxaFrete;

//    @JsonIgnore
    @JsonIgnoreProperties("hibernateLazyInitializer") //ignora a visualização mas realiza a busca quando necessário,
    @ManyToOne(fetch = FetchType.LAZY) //LAZY -> associação preguiçosa , só carrega(select) quando precisar
    @JoinColumn(name = "cozinha_id", nullable=false)
    private Cozinha cozinha;

    @JsonIgnore
    @ManyToMany//(fetch = FetchType.EAGER) //quando o padrao já é buscar, não é necessário ultz o EAGER, aqui é só pra demonstrar
    @JoinTable(name = "tbl_restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"),
                inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formaPagamento = new ArrayList<>();

    @JsonIgnore
    @Embedded //incorporação da classe Endereco
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp //deve ser atribuida com uma data/hora atual no momento que o objeto foi criado
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp // atribui uma data/hora atual no momento que o objeto é atualizado
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;


    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

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
