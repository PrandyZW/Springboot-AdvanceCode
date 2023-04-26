package com.yzw.advance.abstractMethod.method1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudService {

    @Autowired
    private CrudContext crudContext;

    public void save(String moduleName,Object object){
        crudContext.save(moduleName,object);
    }
}
