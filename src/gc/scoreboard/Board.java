package gc.scoreboard;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Board {

	private String nome,ip;
	private ScoreBoard pl = ScoreBoard.plugin;
	
	public Board(Player p){
		this.nome = pl.getConfig().getString("nome");
		this.ip = "aa§6" + pl.getConfig().getString("ip") + " ";
		p.setScoreboard(getBoard());
	}
	
	private Scoreboard getBoard(){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = board.registerNewObjective("gcscore", "dummy");
		
		o.setDisplayName(ChatColor.WHITE + "" +  ChatColor.BOLD + nome);
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Team jogadores = board.registerNewTeam("jogadores"); 
		jogadores.addEntry(ChatColor.AQUA.toString());
		jogadores.setPrefix("  §fJogadores:");
		jogadores.setSuffix(" §e?");
		
		Team grupo = board.registerNewTeam("grupo");
		grupo.addEntry(ChatColor.BLACK.toString());
		grupo.setPrefix("  §fGrupo:");
		grupo.setSuffix(" §7[Membro]");
		
		Team coins = board.registerNewTeam("coins");
		coins.addEntry(ChatColor.BLUE.toString());
		coins.setPrefix("  §fCoins:");
		coins.setSuffix(" §a?");
		
		Team kdr = board.registerNewTeam("kdr");
		kdr.addEntry(ChatColor.BOLD.toString());
		kdr.setPrefix("  §fKDR:");
		kdr.setSuffix(" §6?");
		
		Team clan = board.registerNewTeam("clan");
		clan.addEntry(ChatColor.DARK_AQUA.toString());
		clan.setPrefix("  §fClã:");
		clan.setSuffix(" §7Nenhum");
		
		Team ip = board.registerNewTeam("ip");
		ip.addEntry(ChatColor.DARK_BLUE.toString());
		if(this.ip.length() > 32)Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Ip do servidor grande demais, não ultrapasse 24 caracteres.");
		else if(this.ip.length() > 16){
			String ip1 = StringUtils.left(this.ip, 17);
			ip.setPrefix(ip1.replace("aa", " "));
			String ip2 = this.ip.replace(ip1, "");
			ip2 = "§6"+ ip2;
			ip.setSuffix(ip2);
		}else{
			ip.setPrefix("§6" + this.ip.replace("aa", " "));
		}
		
		
		o.getScore(" ").setScore(9);
		o.getScore(ChatColor.AQUA.toString()).setScore(8);
		o.getScore(ChatColor.BLACK.toString()).setScore(7);
		o.getScore("  ").setScore(6);
		o.getScore(ChatColor.BLUE.toString()).setScore(5);
		o.getScore("   ").setScore(4);
		o.getScore(ChatColor.BOLD.toString()).setScore(3);
		o.getScore(ChatColor.DARK_AQUA.toString()).setScore(2);
		o.getScore("    ").setScore(1);
		o.getScore(ChatColor.DARK_BLUE.toString()).setScore(0);
		
		return board;
	}
	
}
