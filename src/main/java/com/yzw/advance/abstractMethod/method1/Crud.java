package com.yzw.advance.abstractMethod.method1;

import java.util.List;

public interface Crud<P>{

    /**
     * 保存方法
     * @param p
     * @return
     */
    P save(P p);

    /**
     * 更新方法
     * @param p
     * @return
     */
    boolean update(P p);

    /**
     * 删除方法
     * @param p
     * @return
     */
    boolean delete(P p);

    /**
     * 查找方法
     * @param p
     * @return
     */
    List<P> select(P p);

    String moduleName();
}
