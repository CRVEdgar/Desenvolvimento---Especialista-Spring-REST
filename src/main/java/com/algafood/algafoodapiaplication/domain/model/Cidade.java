package com.algafood.algafoodapiaplication.domain.model;

import com.algafood.algafoodapiaplication.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) // c/c @EqualsAndHashCode.Include
@Data
@Entity
@Table(name = "tab_cidade") //NÃO TEM
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
    @Column(nullable = false)
    private String nome;

//    @Valid
//    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class) // c/c @NotNull
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable=false)
    private Estado estado;
    
}
