package com.iitu.services;


import com.iitu.entities.AuditModel;

import java.util.List;

/**
 * @author Assylkhan
 * on 14.12.2018
 * @project qapp
 */
public interface GenericService<T extends AuditModel> {

    public void create(T t);

    public boolean update(T t);

    public T getById(Long id);

    public boolean delete(Long id);

    public List<T> getAll();
}
