package de.alpha.uhc.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.alpha.uhc.Core;
import de.alpha.uhc.utils.Stats;
import de.popokaka.alphalibary.command.SimpleCommand;

public class CoinsCommand extends SimpleCommand<Core> {

    private String rew;
    private Core pl;

    public CoinsCommand(Core plugin, String[] aliases) {
        super(plugin, "coins", "Add coins to a Player", aliases);
        this.pl = plugin;
    }

    public void setRew(String rew) {
        this.rew = rew;
    }

    @Override
    public boolean execute(CommandSender cs, String label, String[] args) {
        if (cs instanceof Player) return false;
        if (args.length == 0) {
            cs.sendMessage(pl.getPrefix() + "�7/coins <add/remove> <amount> <Player>");
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("add")) {
                if (Bukkit.getPlayer(args[2]) != null) {
                    Stats s = new Stats(Bukkit.getPlayer(args[2]));
                    s.addCoins(Integer.parseInt(args[1]));
                    String a = rew.replace("[Coins]", args[1]);
                    Bukkit.getPlayer(args[2]).sendMessage(pl.getPrefix() + a);
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (Bukkit.getPlayer(args[2]) != null) {
                    Stats s = new Stats(Bukkit.getPlayer(args[2]));
                    s.removeCoins(Integer.parseInt(args[1]));
                }
            }
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender cs, String label, String[] args) {
        return null;
    }

}
