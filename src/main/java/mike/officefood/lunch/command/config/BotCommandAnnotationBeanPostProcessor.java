package mike.officefood.lunch.command.config;

import mike.officefood.lunch.command.BotCommandContainer;
import mike.officefood.lunch.command.Command;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component
public class BotCommandAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private BotCommandContainer botCommandContainer;

    @Override
    public Object postProcessAfterInitialization(Object bean, @Nullable String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(BotCommand.class)){
            botCommandContainer.putCommand(bean.getClass().getAnnotation(BotCommand.class).name(),
                    (Command) bean);
        }
        return bean;
    }
}
