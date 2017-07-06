package gc.scoreboard.utils;

import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class ClansUtil {
	public static boolean hasClan(Player p) {
		if (SimpleClans.getInstance().getClanManager().getClanPlayer(p) == null) {
			return false;
		}
		return true;
	}


	public static String ClanTag(Player p) {
		if (hasClan(p)) {
			return SimpleClans.getInstance().getClanManager().getClanPlayer(p).getClan().getColorTag();
		}
		return "Livre";
	}

	public static int ClanMembers(Player p) {
		if (hasClan(p)) {
			return SimpleClans.getInstance().getClanManager().getClanPlayer(p).getClan().getAllMembers().size();
		}
		return 0;
	}

	public static float ClanKdr(Player p) {
		if (hasClan(p)) {
			return SimpleClans.getInstance().getClanManager().getClanPlayer(p).getKDR();
		}
		return 0.0F;
	}
}
