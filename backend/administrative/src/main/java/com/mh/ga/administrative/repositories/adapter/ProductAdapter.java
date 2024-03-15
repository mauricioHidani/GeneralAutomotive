package com.mh.ga.administrative.repositories.adapter;

import javax.swing.text.html.parser.Entity;

public interface ProductAdapter<ENTITY, ID> {
    ENTITY save(ENTITY entity);
}
