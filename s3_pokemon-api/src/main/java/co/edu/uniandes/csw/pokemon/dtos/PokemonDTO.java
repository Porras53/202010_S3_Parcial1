/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.dtos;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import co.edu.uniandes.csw.pokemon.entities.AtaqueEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Porras
 */
public class PokemonDTO 
{
    private String nombre;
    private String descripcion;
    private Double peso;
    private Double altura;
    private PokemonType tipo;
    private PokemonType tipodebilidad;
    //private List<AtaqueEntity> listaataques= new ArrayList<>();
    
    public PokemonDTO()
    {
    }

    /**
     * Convierte un objeto AuthorDTO a AuthorEntity.
     *
     * @return Nueva objeto AuthorEntity.
     *
     */
    public PokemonEntity toEntity() {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setAltura(this.altura);
        pokemonEntity.setNombre(this.nombre);
        pokemonEntity.setDescripcion(this.descripcion);
        pokemonEntity.setPeso(this.peso);
        pokemonEntity.setTipo(this.tipo);
        pokemonEntity.setTipodebilidad(this.tipodebilidad);
       // pokemonEntity.setParadas(this.listaataques);
        return pokemonEntity;
    }
    
    public PokemonDTO(PokemonEntity pokemonEntity) {
        if (pokemonEntity != null) {
            this.altura = pokemonEntity.getAltura();
            this.nombre=pokemonEntity.getNombre();
            this.descripcion=pokemonEntity.getDescripcion();
            this.peso=pokemonEntity.getPeso();
            this.tipo=pokemonEntity.getTipo();
            this.tipodebilidad=pokemonEntity.getTipodebilidad();
           // this.listaataques=pokemonEntity.getParadas();
        }
    }

    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the altura
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * @return the tipo
     */
    public PokemonType getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(PokemonType tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tipodebilidad
     */
    public PokemonType getTipodebilidad() {
        return tipodebilidad;
    }

    /**
     * @param tipodebilidad the tipodebilidad to set
     */
    public void setTipodebilidad(PokemonType tipodebilidad) {
        this.tipodebilidad = tipodebilidad;
    }

//    /**
//     * @return the listaataques
//     */
//    public List<AtaqueEntity> getListaataques() {
//        return listaataques;
//    }
//
//    /**
//     * @param listaataques the listaataques to set
//     */
//    public void setListaataques(List<AtaqueEntity> listaataques) {
//        this.listaataques = listaataques;
//    }
    
    
    
}
