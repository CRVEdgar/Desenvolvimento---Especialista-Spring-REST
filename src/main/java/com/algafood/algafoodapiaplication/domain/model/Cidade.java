package com.algafood.algafoodapiaplication.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) // c/c @EqualsAndHashCode.Include
@Data
@Entity
@Table(name = "tab_cidade") //NÃO TEM
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable=false)
    private Estado estado;
    
}
