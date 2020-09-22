/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.test.logic;

import co.edu.uniandes.csw.pokemon.ejb.PokemonLogic;
import co.edu.uniandes.csw.pokemon.entities.AtaqueEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Manuel Porras
 */
@RunWith(Arquillian.class)
public class PokemonLogicTest {
    
    @Inject
    private PokemonLogic trayectoLogic;

    
    private  List<AtaqueEntity> listaataques= new ArrayList<>();
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PokemonEntity> data = new ArrayList<>();
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
            .addPackage(PokemonEntity.class.getPackage())
            .addPackage(PokemonPersistence.class.getPackage())
            .addPackage(PokemonLogic.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
}


@Before
public void configTest() {
    try {
        utx.begin();
        em.joinTransaction();
        clearData();
        insertData();
        utx.commit();
    } catch (Exception e) {
        e.printStackTrace();
        try {
            utx.rollback();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

/**
    * Limpia las tablas que están implicadas en la prueba.
*/
private void clearData() {
    em.createQuery("delete from PokemonEntity").executeUpdate();
    em.createQuery("delete from AtaqueEntity").executeUpdate();
}

/**
    * Inserta los datos iniciales para el correcto funcionamiento de las
    * pruebas.
*/
private void insertData() {
   
    for (int i = 0; i < 3; i++) {
        AtaqueEntity entity;
        entity = factory.manufacturePojo(AtaqueEntity.class);
        entity.setCantdanio(50);
        
        em.persist(entity);
        listaataques.add(entity);
    } 
    
    for (int i = 0; i < 3; i++) {
        PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);

        em.persist(entity);
        data.add(entity);
    }
   
    
}

@Test
public void createPokemonTest() throws BusinessLogicException {
    PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
    PokemonEntity result = trayectoLogic.createPokemon(newEntity, (ArrayList<AtaqueEntity>) listaataques);
    Assert.assertNotNull(result);
    
    PokemonEntity entity = em.find(PokemonEntity.class, result.getId());
    Assert.assertEquals(newEntity.getId(), entity.getId());   
}

@Test(expected=BusinessLogicException.class)
public void createPokemonTestMalo() throws BusinessLogicException {
    PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
    for (AtaqueEntity ataque: listaataques) {
        ataque.setCantdanio(-1);
    } 
    PokemonEntity result = trayectoLogic.createPokemon(newEntity, (ArrayList<AtaqueEntity>) listaataques);
    Assert.assertNotNull(result);
    
    PokemonEntity entity = em.find(PokemonEntity.class, result.getId());
    Assert.assertEquals(newEntity.getId(), entity.getId());   
}

    
}
