package com.yzw.advance.abstractMethod.method1;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class CrudContext {
    @Resource
    private List<Crud> crudList;

    private LinkedHashMap<String,Crud> crudMap = new LinkedHashMap<>();

    @PostConstruct
    public void init(){
        crudList.stream().forEach(crud -> {
            crudMap.put(crud.moduleName(),crud);
        });
    }

    public void save(String moduleName,Object object){
        Crud crud = crudMap.get(moduleName);
        crud.save(object);
    }
}
