package mike.officefood.commands.order;

import mike.officefood.main.config.Command;
import mike.officefood.main.config.CommandWithMessageService;
import mike.officefood.main.context.ContextBuilder;
import mike.officefood.main.context.CommandContext;
import mike.officefood.main.config.BotCommand;
import mike.officefood.main.result.CommandResult;
import mike.officefood.main.result.SendSimpleMessage;
import mike.officefood.repository.entity.TelegramUser;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@BotCommand(name = "/want")
public class MakeOrderCommand extends CommandWithMessageService implements Command {

    @Override
    public CommandResult execute(CommandContext context) {


        /*String[] messages = update.getMessage().getText().trim().split(" ");
        Integer amount = 1;
        String menuTag = "";
        Date orderDate = new Date();

        if (messages.length > 1) menuTag = messages[1];
        if (messages.length > 2 && messages[2] != null)
            try {
                amount = Integer.parseInt(messages[2]);
            } catch (Exception e) {
                amount = 1;
            }*/

        //.addParam("orderDate", orderDate)
        //.addParam("menu", menuTag)
        //.addParam("amount", amount)

        //String resultMessage = lunchOrderService.makeOrder();
        String resultMessage = "делай";
        return new SendSimpleMessage(context.getChatId(), resultMessage);
    }
}
