package com.algafood.algafoodapiaplication.domain.model;

import com.algafood.algafoodapiaplication.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name ="tab_estado")
public class Estado {

    @NotNull(groups = Groups.EstadoId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable=false)
    private String nome;
}
