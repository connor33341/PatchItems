package org.connor33341.patchItems;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public final class PatchItems extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // If anyone has a better method, besides looping every 5 seconds, please msg me
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    removeIllegal(player);
                }
            }
        }.runTaskTimer(this, 0L, 100L); // 100 ticks = 5 seconds
        getLogger().info("PatchItems has been enabled!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void removeIllegal(Player player){
        for (ItemStack item : player.getInventory().getContents()){
            if (item != null && item.getType() != Material.AIR){
                if (item.containsEnchantment(Enchantment.SHARPNESS)){
                    int level = item.getEnchantmentLevel(Enchantment.SHARPNESS);
                    if (level > 10){
                        player.getInventory().remove(item);
                        player.sendMessage("Illegal items detected, for more info visit: https://connor33341.github.io/mc/patches");
                    }
                }
            }
        }
    }
}
