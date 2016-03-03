package de.alpha.uhc.files;

import de.alpha.uhc.teams.ATeam;
import net.minetopix.library.main.file.SimpleFile;

public class TeamFile {
	
	public static SimpleFile getTeamFile() {
        return new SimpleFile("plugins/UHC", "teams.yml");
    }
	
	private static SimpleFile file = getTeamFile();
	
	public static void addDefaultTeams() {
		
		if(file.isConfigurationSection("Teams") == false) {
			
			file.setDefault("Teams.0.name", "red");
			file.setDefault("Teams.0.color", "red");
			file.setDefault("Teams.0.GUI Block", "wool:14");
			
			file.setDefault("Teams.1.name", "yellow");
			file.setDefault("Teams.1.color", "yellow");
			file.setDefault("Teams.1.GUI Block", "wool:4");
			
			file.setDefault("Teams.2.name", "blue");
			file.setDefault("Teams.2.color", "blue");
			file.setDefault("Teams.2.GUI Block", "wool:11");
			
		}
	}
	
	public static void loadTeams() {
		
		int id = file.getConfigurationSection("Teams").getKeys(false).size();
		for(int a = 0; a < id;a++) {
			ATeam.teamNames.add(file.getString("Teams."+a+".name"));
			ATeam.teamColors.add(file.getString("Teams."+a+".color"));
			ATeam.teamBlocks.add(file.getString("Teams."+a+".GUI Block"));
		}
	}

}
