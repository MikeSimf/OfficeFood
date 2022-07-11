package mike.officefood.main.config;

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
            botCommandContainer.putCommand((Command) bean);
        }
        return bean;
    }
}
