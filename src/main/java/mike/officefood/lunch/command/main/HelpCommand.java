package mike.officefood.lunch.command.main;

import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@BotCommand(name = "/help")
public class HelpCommand extends CommandWithMessageService implements Command {

    @Override
    public void execute(Update update) {
        String message = "Доступные команды:\n" +
                "/regme - регистрация пользователя\n"+
                "/start - старт набора заказа (для админов)\n"+
                "/stop - остановка набора заказа (для админов)\n"+
                "/want tag count - заказ позиции tag в количестве count\n"+
                "/menu - вывод всего меню\n"+
                "/show - вывод заказа\n"+
                "/clear - очистка заказа\n";
        this.sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), message);
    }
}
