package de.alphahelix.uhc.listeners.scenarios;

import de.alphahelix.uhc.Scenarios;
import de.alphahelix.uhc.UHC;
import de.alphahelix.uhc.instances.SimpleListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class TimberListener extends SimpleListener {

    public TimberListener(UHC uhc) {
        super(uhc);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        if (!scenarioCheck(Scenarios.TIMBER)) return;

        Block b = e.getBlock();

        if (b.getType() != Material.LOG && b.getType() != Material.LOG_2) {
            return;
        }

        b = b.getRelative(BlockFace.UP);

        while (b.getType() == Material.LOG || b.getType() == Material.LOG_2) {
            b.breakNaturally();
            b = b.getRelative(BlockFace.UP);
        }
    }
}
