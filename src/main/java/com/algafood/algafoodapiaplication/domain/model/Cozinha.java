package com.algafood.algafoodapiaplication.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algafood.algafoodapiaplication.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
@JsonRootName("gastronomia") //renomea a representação do elemento sem alterar o nome / alt só a representacao
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // c/c @EqualsAndHashCode.Include
@Data //substitue get/set/Equals\hashcode/construtor
@Entity
@Table(name ="tab_cozinhas")
public class Cozinha {

    @NotNull(groups = Groups.CozinhaId.class) //o groups serve para especificar que o @NotNull c/c Validated(do controller) será validado somente para o grupo informado)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    //@JsonIgnore //remove a representacao do atributo [nao deve ser usado em conjunto com @JsonProperty
    @NotBlank
//    @JsonProperty("titulo") //cria um nome que é somente represntativo para o atributo, sem alterar seu nome original(so o nome que aparece na tela)
    @Column//(name="nome_cozinha", length=30)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();

//    public Cozinha() {
//    }
//
//    public Cozinha(Long id, String nome) {
//        this.id = id;
//        this.nome = nome;
//    }

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Cozinha)) return false;
//
//        Cozinha cozinha = (Cozinha) o;
//
//        return id != null ? id.equals(cozinha.id) : cozinha.id == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return id != null ? id.hashCode() : 0;
//    }
}
