/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.gob.ambiente.servicios.especiesforestales.facades;

import ar.gob.ambiente.servicios.especiesforestales.entidades.Cites;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rincostante
 */
@Stateless
public class CitesFacade extends AbstractFacade<Cites> {
    @PersistenceContext(unitName = "ar.gob.ambiente.servicios_especiesForestales_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitesFacade() {
        super(Cites.class);
    }
    
    /**
     * Metodo que verifica si ya existe la entidad.
     * @param aBuscar: es la cadena que buscara para ver si ya existe en la BDD
     * @return: devuelve True o False
     */
    public boolean existe(String aBuscar){
        em = getEntityManager();       
        String queryString = "SELECT cites.nombre FROM Cites cites "
                + "WHERE cites.nombre = :stringParam ";
        
        Query q = em.createQuery(queryString)
                .setParameter("stringParam", aBuscar);
        return q.getResultList().isEmpty();
    }  
    
    /**
     * Método que verifica si la entidad tiene dependencias
     * @param id: ID de la entidad
     * @return: True o False
     */
    public boolean noTieneDependencias(Long id){
        em = getEntityManager();       
        String queryString = "SELECT esp FROM Especie esp " 
                + "WHERE esp.cites.id = :id";      
        Query q = em.createQuery(queryString)
                .setParameter("id", id);
        return q.getResultList().isEmpty();
    }          
    
}