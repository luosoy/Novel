/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luosoy.frame.jpa.identity;

import java.util.UUID;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class UUIDProducer implements IdProducer{

    @Override
    public Sequence produce(Context ctx) {
        return new Sequence(UUID.randomUUID().toString().replace("-", "").toUpperCase());
    }

}
