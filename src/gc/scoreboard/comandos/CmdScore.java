package gc.scoreboard.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gc.scoreboard.Board;
import gc.scoreboard.ScoreBoard;

public class CmdScore implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("score")){
			if(!p.hasPermission("gcscore.usar")){
				p.sendMessage("§aVocê não possui permissão para usar este comando.");
				return false;
			}
			
			if(p.getScoreboard().getObjectives().isEmpty()){
				new Board(p);
				if(ScoreBoard.scoreOff.contains(p)){
					ScoreBoard.scoreOff.remove(p);
				}
				p.sendMessage("§aScoreboard habilitada com sucesso.");
				return true;
			}
			
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			ScoreBoard.scoreOff.add(p);
			p.sendMessage("§aScoreboard desabilitada com sucesso.");
			return true;
			
		}
		
	return false;
	}

}
