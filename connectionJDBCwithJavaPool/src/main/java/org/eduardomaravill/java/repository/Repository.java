package org.eduardomaravill.java.repository;

import java.util.List;

public interface Repository<T> {
    List<T> readAll();
    T byId(Long id);
    void save(T t);
    void delete(Long id);
}
