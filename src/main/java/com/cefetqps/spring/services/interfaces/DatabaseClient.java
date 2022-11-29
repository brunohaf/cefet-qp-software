package com.cefetqps.spring.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public interface DatabaseClient<T> {

    public abstract boolean getConnetion();

    public abstract Collection<T> getAll();

    public abstract Optional<T> getById(int id);

    public abstract boolean save(T element);
}
