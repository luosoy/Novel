/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.common.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author luozp
 */
@Configuration
public class EntityManagerConfig {

    @PersistenceContext(unitName = "test-db")
    private EntityManager em;

    @Bean(name = "NativeEntityManager")
    public EntityManager produceNativeEntityManager() {
        return em;
    }
}
