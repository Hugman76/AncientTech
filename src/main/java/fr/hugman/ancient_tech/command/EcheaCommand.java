package fr.hugman.ancient_tech.command;

import com.mojang.brigadier.CommandDispatcher;
import fr.hugman.ancient_tech.echea.IncorporealEchea;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class EcheaCommand {
	public static final String NAME = "echea";
	public static final String QUERY_ARG = "query";

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal(NAME).requires(sc -> sc.hasPermissionLevel(2))
				.then(CommandManager.literal(QUERY_ARG)
						.executes(context -> query(context.getSource()))));
	}

	private static int query(ServerCommandSource source) {
		var echea = IncorporealEchea.get(source.getWorld());
		if(echea.isEmpty()) {
			source.sendFeedback(Text.of("The incorporeal echea is empty."), false);
			return 1;
		}
		var text = Text.literal("The incorporeal echea contains:");
		for(var stack : echea) {
			text = text.append("\n").append(stack.getCount() + "x ").append(stack.getName());
		}
		source.sendFeedback(text, false);
		return 1;
	}
}
