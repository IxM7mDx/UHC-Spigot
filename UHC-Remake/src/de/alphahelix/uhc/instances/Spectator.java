package de.alphahelix.uhc.instances;

import de.alphahelix.alphalibary.item.ItemBuilder;
import de.alphahelix.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spectator {

    public Spectator(final Player p) {
        Location l = p.getLocation();
        p.spigot().respawn();
        p.teleport(l);
        UHC.getInstance().getRegister().getPlayerUtil().clearUp(p);
        p.setCanPickupItems(false);
        p.setVelocity(p.getVelocity().setY(20D));
        p.setTotalExperience(0);
        p.setGameMode(GameMode.ADVENTURE);
        p.setPlayerListName("§7[§4X§7] §c" + p.getPlayerListName());
        p.setAllowFlight(true);
        p.setFlying(true);
        try {
            if(UHC.getInstance().isOneNine()) {
                Class.forName("org.bukkit.entity.Entity").getMethod("setInvulnerable", boolean.class).invoke(p, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new BukkitRunnable() {
            public void run() {
                equipSpecStuff(p);
            }
        }.runTaskLater(UHC.getInstance(), 20);
        for (String ig : UHC.getInstance().getRegister().getPlayerUtil().getSurvivors()) {
            Bukkit.getPlayer(ig).hidePlayer(p);
        }
    }

    public Spectator(final Player p, Location l) {
        p.teleport(l);
        UHC.getInstance().getRegister().getPlayerUtil().clearUp(p);
        p.setCanPickupItems(false);
        p.setVelocity(p.getVelocity().setY(20D));
        p.setTotalExperience(0);
        p.setGameMode(GameMode.ADVENTURE);
        p.setPlayerListName("§7[§4X§7] §c" + p.getDisplayName());
        p.setAllowFlight(true);
        p.setFlying(true);
        new BukkitRunnable() {
            public void run() {
                equipSpecStuff(p);
            }
        }.runTaskLater(UHC.getInstance(), 20);
        for (String ig : UHC.getInstance().getRegister().getPlayerUtil().getSurvivors()) {
            Bukkit.getPlayer(ig).hidePlayer(p);
        }
    }

    private void equipSpecStuff(Player p) {
        p.getInventory().setItem(UHC.getInstance().getRegister().getSpectatorFile().getInt("Spectator.Item Slot"),
                new ItemBuilder(Material.getMaterial(UHC.getInstance().getRegister().getSpectatorFile()
                        .getString("Spectator.Item").replace(" ", "_").toUpperCase()))
                        .setName(UHC.getInstance().getRegister().getSpectatorFile()
                                .getColorString("Spectator.Item Name"))
                        .build());
    }
}
