package mike.officefood.commands.common;

import mike.officefood.main.config.BotCommand;
import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import org.springframework.stereotype.Component;

@Component
@BotCommand(name = "/help")
public class HelpCommand extends CommandWithMessageService implements Command {

    public CommandResult execute(CommandContext context) {
        String message = "Доступные команды:\n" +
                "/regme - регистрация пользователя\n"+
                "/start - старт набора заказа (для админов)\n"+
                "/stop - остановка набора заказа (для админов)\n"+
                "/want tag count - заказ позиции tag в количестве count\n"+
                "/menu - вывод всего меню\n"+
                "/show - вывод заказа\n"+
                "/clear - очистка заказа\n";
        return new SendSimpleMessage(context.getChatId(), message);
    }
}
