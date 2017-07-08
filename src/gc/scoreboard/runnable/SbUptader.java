package gc.scoreboard.runnable;

import java.text.DecimalFormat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import gc.scoreboard.ScoreBoard;
import gc.scoreboard.eventos.PegarRankEvento;
import gc.scoreboard.utils.ClansUtil;
import net.milkbowl.vault.economy.Economy;

public class SbUptader extends BukkitRunnable {
	
	private ScoreBoard pl = ScoreBoard.plugin;
	private PegarRankEvento rankevent = PegarRankEvento.getRank; 
	
	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()){
			if(!ScoreBoard.scoreOff.contains(p)){
				Scoreboard board = p.getScoreboard();
				
				Team jogadores = board.getTeam("jogadores");
				jogadores.setSuffix(" §e" + Bukkit.getOnlinePlayers().size());
				
				Team grupo = board.getTeam("grupo");
				if(ScoreBoard.chat.getPlayerPrefix(p).equalsIgnoreCase("")){
					grupo.setSuffix(" §7[Membro]");
				}else{
					grupo.setSuffix(" " + ScoreBoard.chat.getPlayerPrefix(p).replace("&", "§"));
				}
				
				if(pl.uranking) {
					
					Team ranker = board.getTeam("rank");
					ranker.setSuffix(rankevent.rank);
					
				}
				
				Team clan = board.getTeam("clan");
				if(ClansUtil.hasClan(p)){
					clan.setSuffix(" " + ClansUtil.ClanTag(p));
				}else{
					clan.setSuffix(" §7Nenhum");
				}
				
				Team kdr = board.getTeam("kdr");
				kdr.setSuffix(" §6" + ClansUtil.ClanKdr(p));

				Team coins = board.getTeam("coins");
				coins.setSuffix(" §a" + money(p));
			}
		}
	}
	@SuppressWarnings("deprecation")
	public String money(Player p) {
		Economy econ = ScoreBoard.economy;
		double money = econ.getBalance(p.getName());
		int monao = (int) econ.getBalance(p.getName());
		if (money >= 1000000000) {
			return new DecimalFormat("0.00B").format(money * 1.0D / 100000000.0D);
		} else if (money >= 1000000) {
			return new DecimalFormat("0.00M").format(money * 1.0D / 1000000D);
		} else if (money >= 1000) {
			return new DecimalFormat("0.00k").format(money * 1.0D / 1000D);
		} else if (money >= 1) {
			return String.valueOf(monao) + ".0";
		} else {
			return "0";
		}
	}
	
}
