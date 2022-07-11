package mike.officefood.main.config;

import mike.officefood.main.context.CommandContext;
import mike.officefood.main.result.CommandResult;

public interface Command {
    CommandResult execute(CommandContext context);
}
