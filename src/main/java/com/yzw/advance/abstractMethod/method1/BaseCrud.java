package com.yzw.advance.abstractMethod.method1;

import com.yzw.advance.abstractMethod.method2.OperatorCallback;

import java.util.Arrays;
import java.util.List;

public abstract class BaseCrud<P> implements Crud<P>{



    @Override
    public P save(P p) {
        return p;
    }

    @Override
    public boolean update(P p) {
        return true;
    }

    @Override
    public boolean delete(P p) {
        return true;
    }

    @Override
    public List<P> select(P p) {
        return Arrays.asList(p);
    }
}
