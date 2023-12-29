package com.mjc.school.repository;


import com.mjc.school.repository.model.BaseEntity;

import java.util.List;
import java.util.Optional;


public  interface BaseRepository <E extends BaseEntity<T>, T>{
     List<E> readAll();
     Optional<E> readById(T id);
     E create(E entity);
     E update(E entity);
     boolean deleteById(T id);
}
