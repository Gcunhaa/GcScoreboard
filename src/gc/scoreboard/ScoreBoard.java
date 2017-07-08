package gc.scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import gc.scoreboard.comandos.CmdScore;
import gc.scoreboard.eventos.EventoEntrarSair;
import gc.scoreboard.eventos.PegarRankEvento;
import gc.scoreboard.runnable.SbUptader;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class ScoreBoard extends JavaPlugin {

	public static ScoreBoard plugin;
	public static Economy economy;
	public static List<Player> scoreOff = new ArrayList<Player>();
	public static Chat chat;
	public boolean uranking = false;

	@Override
	public void onEnable() {
		plugin = this;

		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(ChatColor.GREEN + "#############################");
		console.sendMessage(ChatColor.GREEN + "# GcScoreboard by @GcunhaBR #");
		console.sendMessage(ChatColor.GREEN + "#############################");

		if (!new File(getDataFolder(), "config.yml").exists()) {
			console.sendMessage(ChatColor.BLUE + "[GcScoreboard] Config.yml nao encontrada, gerando uma nova...");
			saveDefaultConfig();
		}

		if(Bukkit.getPluginManager().isPluginEnabled("uRanking")) {
			
			console.sendMessage(ChatColor.BLUE + "[GcScoreboard] uRanking encontrado! Compatibilidade ativa.");
			
		}
		
		setupEventos();
		setupComandos();
		setupRunnable();
		setupEconomy();
		setupChat();
	}

	private void setupEventos() {
		Bukkit.getPluginManager().registerEvents(new EventoEntrarSair(), this);
		Bukkit.getPluginManager().registerEvents(new PegarRankEvento(this), this);
	}

	private void setupRunnable() {
		new SbUptader().runTaskTimer(this, 0l, 15 * 20l);
	}

	private void setupComandos() {
		getCommand("score").setExecutor(new CmdScore());
	}

	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

	private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
}
