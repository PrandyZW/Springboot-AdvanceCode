package com.yzw.advance.abstractMethod.method1;

import com.yzw.advance.common.entity.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankCrud extends BaseCrud<Bank>{

    @Override
    public String moduleName() {
        return "银行模块";
    }
}
