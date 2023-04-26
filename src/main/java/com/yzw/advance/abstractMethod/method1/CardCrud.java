package com.yzw.advance.abstractMethod.method1;

import com.yzw.advance.common.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardCrud extends BaseCrud<Card>{

    @Override
    public String moduleName() {
        return "卡片模块";
    }
}
