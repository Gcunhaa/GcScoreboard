package gc.scoreboard.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import gc.scoreboard.Board;
import gc.scoreboard.ScoreBoard;

public class EventoEntrarSair implements Listener {
	
	@EventHandler()
	public void aoEntrar(PlayerJoinEvent e){
		Player p = e.getPlayer();
		new Board(p);
	}
	
	
	@EventHandler
	public void aoSair(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(ScoreBoard.scoreOff.contains(p)){
			ScoreBoard.scoreOff.remove(p);
		}
	}
	
}
