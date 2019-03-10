package me.robnoo02.prefixtitle;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrefixTitleCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(cmd.getName().equalsIgnoreCase("prefixtitle") || cmd.getName().equalsIgnoreCase("ptitle")))
			return true;
		if(!(sender instanceof Player))
			return true;
		Player p = (Player) sender;
		if(args.length == 0)
			return ChatMessage.HELP.send(p);
		switch(args[0]) {
		case "info":
			return ChatMessage.INFO.send(p);
		case "open":
			// Open gui
			return ChatMessage.NOT_ADDED_YET.send(p);
		case "set":
			if(args.length == 1)
				return ChatMessage.SPECIFY_TITLE.send(p);
			String title = args[1];
			if(title.equalsIgnoreCase("none")) {
				ConfigManager.removeTitle(p);
				return ChatMessage.TITLE_REMOVED.send(p);
			}
			if(!TitleManager.getInstance().isTitle(title))
				return ChatMessage.TITLE_NOT_EXIST.send(p);
			if(!p.hasPermission("prefixtitle.set." + title))
				return ChatMessage.CANT_EQUIP.send(p);
			TitleManager.getInstance().setTitle(p, title);
			return ChatMessage.SET_TITLE.send(p, TitleManager.getInstance().getTitle(p).getPrefix());
		case "none":
			ConfigManager.removeTitle(p);
			return ChatMessage.TITLE_REMOVED.send(p);
		case "reload":
			if(!p.hasPermission("prefixtitle.reload"))
				return ChatMessage.NO_PERM.send(p);
			Main.getInstance().reloadConfig();
			return ChatMessage.CONFIG_RELOADED.send(p);
		default:
			return ChatMessage.HELP.send(p);
		}
	}

	
	
}
