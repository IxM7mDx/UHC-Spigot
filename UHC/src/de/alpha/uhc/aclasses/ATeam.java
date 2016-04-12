package de.alpha.uhc.aclasses;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.alpha.uhc.Core;
import de.alpha.uhc.GState;
import de.alpha.uhc.files.MessageFileManager;
import de.alpha.uhc.files.TeamFile;
import de.popokaka.alphalibary.item.ItemBuilder;

public class ATeam implements Listener {
	
	private static String chosen;
	private static String noExist;
	private static String allTeams;
	
	public static synchronized void setChosen(String chosen) {
		ATeam.chosen = chosen;
	}

	public static synchronized void setNoExist(String noExist) {
		ATeam.noExist = noExist;
	}

	public static synchronized void setAllTeams(String allTeams) {
		ATeam.allTeams = allTeams;
	}

	public static synchronized Inventory getTeamInv() {
		return teamInv;
	}

	public static synchronized void setTeamInv(Inventory teamInv) {
		ATeam.teamInv = teamInv;
	}

	public static synchronized ArrayList<String> getTeamNames() {
		return teamNames;
	}

	public static synchronized void setTeamNames(ArrayList<String> teamNames) {
		ATeam.teamNames = teamNames;
	}

	public static synchronized ArrayList<String> getTeamColors() {
		return teamColors;
	}

	public static synchronized void setTeamColors(ArrayList<String> teamColors) {
		ATeam.teamColors = teamColors;
	}

	public static synchronized HashMap<String, Integer> getTeamMax() {
		return teamMax;
	}

	public static synchronized void setTeamMax(HashMap<String, Integer> teamMax) {
		ATeam.teamMax = teamMax;
	}

	public static synchronized HashMap<String, Integer> getTeamC() {
		return teamC;
	}

	public static synchronized void setTeamC(HashMap<String, Integer> teamC) {
		ATeam.teamC = teamC;
	}

	public static synchronized HashMap<Player, String> getTeams() {
		return teams;
	}

	public static synchronized void setTeams(HashMap<Player, String> teams) {
		ATeam.teams = teams;
	}

	public static synchronized String getChosen() {
		return chosen;
	}

	public static synchronized String getNoExist() {
		return noExist;
	}

	public static synchronized String getAllTeams() {
		return allTeams;
	}

	public static synchronized String getMaterialName() {
		return materialName;
	}

	public static synchronized String getBlockName() {
		return blockName;
	}

	public static synchronized String getTitle() {
		return title;
	}

	public static synchronized String getFull() {
		return full;
	}

	private static String materialName;
	private static String blockName;
	private static String title;
	private static String full;
	
	
	
	public static synchronized void setMaterialName(String materialName) {
		ATeam.materialName = materialName;
	}

	public static synchronized void setBlockName(String blockName) {
		ATeam.blockName = blockName;
	}

	public static synchronized void setTitle(String title) {
		ATeam.title = title;
	}

	public static synchronized void setFull(String full) {
		ATeam.full = full;
	}

	private static Inventory teamInv;
	
	private static ArrayList<String> teamNames = new ArrayList<String>();
	private static ArrayList<String> teamColors = new ArrayList<String>();
	
	private static HashMap<String, Integer> teamMax = new HashMap<String, Integer>();
	private static HashMap<String, Integer> teamC = new HashMap<String, Integer>();
	
	private static HashMap<Player, String> teams = new HashMap<Player, String>();
	
	public static void removePlayerFromTeam(Player p) {
		if(teamC.containsKey(getPlayerTeam(p))) {
			teamC.put(getPlayerTeam(p), teamC.get(getPlayerTeam(p))-1);
		}
		if(teams.containsKey(p)) {
			teams.remove(p);
		}
	}
	
	public static void addPlayerToTeam(Player p, String teamToPut) {
		if(teamNames.contains(teamToPut)) {
			if(isFull(teamToPut)) {
				full = full.replace("[team]", getTeamColor(teamToPut)+teamToPut);
				p.sendMessage(Core.getPrefix() + full);
				full = MessageFileManager.getMSGFile().getColorString("Teams.full");
				return;
			} else {
				if(teamC.containsKey(teamToPut)) {
					if(!(teams.containsKey(p))) {
						teamC.put(teamToPut, teamC.get(teamToPut)+1);
					}
				} else {
					teamC.put(teamToPut, 1);
					if(teamC.get(teamToPut) > teamMax.get(teamToPut)) {
						teamC.put(teamToPut, teamC.get(teamToPut)-1);
						full = full.replace("[team]", getTeamColor(teamToPut)+teamToPut);
						p.sendMessage(Core.getPrefix() + full);
						full = MessageFileManager.getMSGFile().getColorString("Teams.full");
						return;
					}
				}
				
				teams.put(p, teamToPut);
				chosen = chosen.replace("[team]", getTeamColor(teamToPut)+teamToPut);
				p.sendMessage(Core.getPrefix() + chosen);
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 0);
				p.spigot().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0, 0, 5, 5, 5, 50, 100, 5);
				p.setDisplayName(getTeamColor(teamToPut) + p.getName());
				p.setPlayerListName(getTeamColor(teamToPut) + p.getName());
				AScoreboard.updateLobbyTeam(p);
				chosen = MessageFileManager.getMSGFile().getColorString("Teams.chosen");
			}
		} else {
			String a = noExist.replace("[team]", teamToPut);
			String b = allTeams.replace("[teams]", ""+teamNames);
			p.sendMessage(Core.getPrefix() + a + "\n       " + b);
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		
		if(!(e.getEntity() instanceof Player && e.getDamager() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		Player other = (Player) e.getDamager();
		
		if(hasSameTeam(p, other)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDestroy(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof ArmorStand) e.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if(!(GState.isState(GState.LOBBY))) return;
		
		Player p = e.getPlayer();
		Material m = p.getInventory().getItemInMainHand().getType();
		Material toCompare = Material.getMaterial(materialName.toUpperCase());
		
		if(m.equals(toCompare)) {
			
			teamInv  = Bukkit.createInventory(null, 36, title);
			
			for(String name : teamNames) {
				
				ItemStack item = new ItemBuilder(Material.getMaterial(blockName.toUpperCase()))
						.setName(getTeamColor(name) + name)
						.setDamage((short) getTeamColorAsInteger(name))
						.build();
						
				
				teamInv.addItem(item);
			}
			p.openInventory(teamInv);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if(!(GState.isState(GState.LOBBY))) return;
		if(e.getClickedInventory() == null) return;
		if(e.getClickedInventory().getTitle() == null) return;
		if(!(e.getClickedInventory().getTitle().equals(title))) return;
		if(!(e.getWhoClicked() instanceof Player)) return;
		if(!(e.getCurrentItem().hasItemMeta())) return;
		if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		
		Player p =(Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem().getType().equals(Material.getMaterial(blockName.toUpperCase()))) {
			
			int dura = e.getCurrentItem().getDurability();
			
			for(String names : teamNames) {
					
				if(dura == getTeamColorAsInteger(names)) {
					addPlayerToTeam(p, names);
					p.closeInventory();
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onTeamJoin(PlayerInteractAtEntityEvent e) {
		if(!(e.getRightClicked() instanceof ArmorStand)) return;
		if(!(GState.isState(GState.LOBBY))) return;
		ArmorStand as = (ArmorStand) e.getRightClicked();
		if(!(as.isCustomNameVisible())) return;
		String name = "";
		for(String names : TeamFile.getTeamFile().getConfigurationSection("Teams").getKeys(false)) {
			if(ChatColor.stripColor(as.getCustomName()).equalsIgnoreCase(names)) {
				name = names;
			}
		}
		
		e.setCancelled(true);
		addPlayerToTeam(e.getPlayer(), name);
	}
	
	public static ChatColor getTeamColor(String team) {
		try {
			return ChatColor.valueOf(TeamFile.getTeamColorAsString(team));
		} catch (IllegalArgumentException e) {
			Bukkit.getConsoleSender().sendMessage(Core.getPrefix() + "�cThe Team �4" + team + " �cis invalid.");
		}
		return ChatColor.RESET;
	}
	
	public static Color getTeamItemColor(String team) {
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("orange")) {
			return Color.ORANGE;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("light_purple")) {
			return Color.FUCHSIA;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("aqua")) {
			return Color.AQUA;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("yellow")) {
			return Color.YELLOW;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("green")) {
			return Color.LIME;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("red")) {
			return Color.MAROON;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_grey")) {
			return Color.GRAY;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("grey")) {
			return Color.SILVER;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_aqua")) {
			return Color.NAVY;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_purple")) {
			return Color.PURPLE;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("blue")) {
			return Color.BLUE;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_green")) {
			return Color.OLIVE;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_red")) {
			return Color.RED;
		}
		if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("black")) {
			return Color.BLACK;
		}
		
		return Color.WHITE;
	}
	
	public static int getTeamColorAsInteger(String team) {
		try {
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("orange")) {
				return 1;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("light_purple")) {
				return 2;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("aqua")) {
				return 3;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("yellow")) {
				return 4;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("green")) {
				return 5;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("red")) {
				return 6;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_grey")) {
				return 7;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("grey")) {
				return 8;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_aqua")) {
				return 9;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_purple")) {
				return 10;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("blue")) {
				return 11;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_green")) {
				return 13;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("dark_red")) {
				return 14;
			}
			if(TeamFile.getTeamColorAsString(team).equalsIgnoreCase("black")) {
				return 15;
			}
			
		} catch (IllegalArgumentException e) {
			Bukkit.getConsoleSender().sendMessage(Core.getPrefix() + "�cThe Team �4" + team + " �cis invalid.");
		}
		
		return 12;
	}
	
	public static boolean hasTeam(Player p) {
		if(teams.containsKey(p)) {
			return true;
		}
		return false;
	}
	
	public static String getPlayerTeam(Player p) {
		if(teams.containsKey(p)) {
			return teams.get(p);
		}
		return "";
	}
	
	public static int getMaxPlayers(String teamToCheck) {
		return teamMax.get(teamToCheck);
	}
	
	public static boolean isFull(String team) {
		if(teamC.containsKey(team)) {
			if(teamC.get(team) == teamMax.get(team)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasSameTeam(Player p, Player other) {
		if(teams.containsKey(p) && teams.containsKey(other)) {
			if(getPlayerTeam(p).equals(getPlayerTeam(other))) {
				return true;
			}
		}
		return false;
	}
}
