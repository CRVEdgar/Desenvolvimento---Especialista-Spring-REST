package com.algafood.algafoodapiaplication.domain.model;


import com.algafood.algafoodapiaplication.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

//    @NotNull //vrf se é null
//    @NotEmpty //vrf se está vazio ou nulo
    @NotBlank // vrf se é nulo, vazio ou se tem espaço em branco
    @Column(nullable=false) // [não] aceita valores nulos
    private String nome;

//    @DecimalMin("0")
    @PositiveOrZero //maior ou igual a zero
    @Column(name = "taxa_frete", nullable=false)
    private BigDecimal taxaFrete;


    @Valid //para verificação em cascata no objeto Cozinha
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne//(fetch = FetchType.LAZY) //LAZY -> associação preguiçosa , só carrega(select) quando precisar
    @JoinColumn(name = "cozinha_id", nullable=false)
    private Cozinha cozinha;

    @ManyToMany//(fetch = FetchType.EAGER) //quando o padrao já é buscar, não é necessário ultz o EAGER, aqui é só pra demonstrar
    @JoinTable(name = "tbl_restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"),
                inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formaPagamento = new ArrayList<>();

    @Embedded //incorporação da classe Endereco
    private Endereco endereco;

    @CreationTimestamp //deve ser atribuida com uma data/hora atual no momento que o objeto foi criado
    @Column(nullable = false)
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp // atribui uma data/hora atual no momento que o objeto é atualizado
    @Column(nullable = false)
    private OffsetDateTime dataAtualizacao;


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
