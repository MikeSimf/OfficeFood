package mike.officefood.lunch.command;

import mike.officefood.lunch.command.config.BotCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@BotCommand(name = "/help")
public class HelpCommand extends CommandWithMessageService implements Command{

    @Override
    public void execute(Update update) {
        this.sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Доступных команд нет");
    }
}
