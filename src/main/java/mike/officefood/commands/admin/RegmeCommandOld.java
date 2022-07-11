package mike.officefood.commands.admin;

import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import mike.officefood.main.config.Command;
import mike.officefood.main.config.BotCommand;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.repository.entity.TelegramUser;
import mike.officefood.repository.entity.UserRole;
import org.springframework.stereotype.Component;


@Component
@BotCommand(name = "/regme")
public class RegmeCommandOld extends CommandWithMessageService implements Command {

    public CommandResult execute(CommandContext context) {
        String resultMessage;

        if (!"private".equals(context.getChatType())) {
            resultMessage = "Для регистрации напишите боту в личку";
        }

        TelegramUser user = context.getUser();
        if (user == null) {
            TelegramUser telegramUser = new TelegramUser();
            telegramUser.setActive(true);
            telegramUser.setChatId(context.getChatId());
            telegramUser.setRole(UserRole.USER);

            if (lunchTelegramUserService.addUser(telegramUser)){
                resultMessage = "Пользователь успешно зарегистрирован";
            } else {
                resultMessage = "Не удалось зарегистрировать пользователя";
            }

        } else {
            user.setActive(true);
            if (user.getRole() == null) {
                user.setRole(UserRole.USER);
            }

            lunchTelegramUserService.addUser(user);
            resultMessage = "Пользователь уже был зарегистрирован";
        }

        return new SendSimpleMessage(context.getChatId(), resultMessage);
    }
}
