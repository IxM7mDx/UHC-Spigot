package de.alphahelix.uhc.listeners.scenarios;

import de.alphahelix.uhc.Scenarios;
import de.alphahelix.uhc.UHC;
import de.alphahelix.uhc.events.timers.LobbyEndEvent;
import de.alphahelix.uhc.instances.SimpleListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DungeonMazeListener extends SimpleListener {

    private HashMap<String, Integer> mined = new HashMap<>();

    public DungeonMazeListener(UHC uhc) {
        super(uhc);
    }

    @EventHandler
    public void onEnd(LobbyEndEvent e) {
        if (!scenarioCheck(Scenarios.DUNGEON_MAZE))
            return;

        for (Player p : makeArray(getRegister().getPlayerUtil().getSurvivors())) {
            p.getInventory().addItem(new ItemStack(Material.TORCH));
        }
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        if (e.isCancelled())
            return;
        if (!scenarioCheck(Scenarios.DUNGEON_MAZE))
            return;

        if (!e.getBlock().getType().equals(Material.STONE)) return;

        if (!mined.containsKey(e.getPlayer().getName())) {
            mined.put(e.getPlayer().getName(), 1);
        } else {
            if (mined.get(e.getPlayer().getName()) == 20) {
                e.getPlayer().getInventory().addItem(new ItemStack(Material.TORCH));
            } else {
                mined.put(e.getPlayer().getName(), mined.get(e.getPlayer().getName()) + 1);
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (e.isCancelled())
            return;
        if (!scenarioCheck(Scenarios.DUNGEON_MAZE))
            return;

        if (e.getRecipe().getResult().getType().equals(Material.TORCH))
            e.setCancelled(true);
    }
}
