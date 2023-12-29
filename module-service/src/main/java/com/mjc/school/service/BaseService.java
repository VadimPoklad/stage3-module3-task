package com.mjc.school.service;

import java.util.List;

public interface BaseService<S, Q, K> {
    S create(Q requestDto) throws Exception;
    List<S> getAll() throws Exception;
    S getById(K id) throws Exception;
    S updateById(Q requestDto) throws Exception;
    boolean removeById(K id) throws Exception;
}
