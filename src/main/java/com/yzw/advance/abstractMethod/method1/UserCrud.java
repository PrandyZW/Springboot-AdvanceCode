package com.yzw.advance.abstractMethod.method1;

import com.yzw.advance.common.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCrud extends BaseCrud<User> {

    @Override
    public String moduleName() {
        return "用户";
    }
}
