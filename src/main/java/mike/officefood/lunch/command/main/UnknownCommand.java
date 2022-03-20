package mike.officefood.lunch.command.main;

import mike.officefood.lunch.command.Command;
import mike.officefood.lunch.command.CommandWithMessageService;
import mike.officefood.lunch.command.config.BotCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@BotCommand(name = "/unknown")
public class UnknownCommand extends CommandWithMessageService implements Command {
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Я не знаю этой команды:(");
    }
}
