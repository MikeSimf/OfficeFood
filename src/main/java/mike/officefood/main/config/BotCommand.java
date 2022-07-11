package mike.officefood.main.config;

import mike.officefood.repository.entity.UserPermission;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BotCommand {

    String name();
    UserPermission[] permissions() default {};

}
