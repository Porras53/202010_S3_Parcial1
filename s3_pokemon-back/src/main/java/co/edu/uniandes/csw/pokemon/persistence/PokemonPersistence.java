/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manuel Porras
 */
@Stateless
public class PokemonPersistence {
    
    
    @PersistenceContext(unitName = "pokemonPU")
    protected EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(PokemonPersistence.class.getName());
    
    public PokemonEntity create(PokemonEntity pokemon )
    {
        //throw new java.lang.UnsupportedOperationException("Not Supported Yet.");
       em.persist(pokemon);
       return pokemon;
    }
    
    /**
    * Devuelve todas las ciudades de la base de datos.
    *
    * @return una lista con todas las ciudades que encuentre en la base de
    * datos. "select u from CityEntity u" es como un "select * from
     * CityEntity;" - "SELECT * FROM table_name" en SQL
*/

public List<PokemonEntity> findAll() {
    LOGGER.log(Level.INFO, "Consultando todas los trayectos");
    // Se crea un query para buscar todas las ciudades en la base de datos.
    TypedQuery query = em.createQuery("select u from PokemonEntity u", PokemonEntity.class);
    // Note que en el query se hace uso del método getResultList() que obtiene una lista de ciudades.
    return query.getResultList();
}
/**
    * Busca si hay alguna ciudad con el id que se envía de argumento
    *
    * @param pokemonId: id correspondiente a la ciudad buscada.
    * @return una ciudad.
    */
public PokemonEntity find(Long pokemonId) {
    LOGGER.log(Level.INFO, "Consultando la ciudad con id={0}", pokemonId);
    /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
    el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
    Suponga que es algo similar a "select * from CityEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
    */
    return em.find(PokemonEntity.class, pokemonId);
}
public PokemonEntity findByNombre(String nombre) {
        LOGGER.log(Level.INFO, "Consultando ciudades por nombre ", nombre);
    // Se crea un query para buscar ciudades con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
    TypedQuery query = em.createQuery("Select e From PokemonEntity e where e.nombre = :nombre", PokemonEntity.class);
    // Se remplaza el placeholder ":isbn" con el valor del argumento 
    query = query.setParameter("nombre", nombre);
    // Se invoca el query se obtiene la lista resultado
    List<PokemonEntity> sameName = query.getResultList();
    PokemonEntity result;
    if (sameName == null) {
        result = null;
    } else if (sameName.isEmpty()) {
        result = null;
    } else {
        result = sameName.get(0);
    }
    LOGGER.log(Level.INFO, "Saliendo de consultar ciudades por nombre ", nombre);
    return result;
    }
}
