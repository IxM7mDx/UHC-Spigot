package de.alphahelix.uhc.listeners.scenarios;

import de.alphahelix.alphalibary.item.ItemBuilder;
import de.alphahelix.uhc.Scenarios;
import de.alphahelix.uhc.UHC;
import de.alphahelix.uhc.events.timers.LobbyEndEvent;
import de.alphahelix.uhc.instances.SimpleListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class BombersListener extends SimpleListener {

    public BombersListener(UHC uhc) {
        super(uhc);
    }

    @EventHandler
    public void onEnd(LobbyEndEvent e) {
        if (!scenarioCheck(Scenarios.BOMBERS))
            return;
        for (Player p : makeArray(getRegister().getPlayerUtil().getSurvivors())) {
            p.getInventory().addItem(new ItemBuilder(Material.FLINT_AND_STEEL).setUnbreakable(true).build());
        }
    }
}
