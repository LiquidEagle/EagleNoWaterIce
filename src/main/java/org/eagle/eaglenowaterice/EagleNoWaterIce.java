package org.eagle.eaglenowaterice;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EagleNoWaterIce extends JavaPlugin {
    Logger log;

    final HashMap NoMeltBuildUsers = new HashMap<Object, Object>();

    boolean noMelting;

    boolean iceDrop;

    boolean noFreeze;

    boolean iceWater;

    String designatedWorldName;

    String designatedWorldName2;

    String designatedWorldName3;

    String designatedWorldName4;

    String designatedWorldName5;

    public EagleNoWaterIce() {
        this.log = Logger.getLogger("Minecraft");
    }
    public void onDisable() {
        this.log.info("Disabled NoMelt");
    }

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new NoMeltBlockListener(this), (Plugin)this);

        FileConfiguration fileConfiguration = getConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        this.noMelting = fileConfiguration.getBoolean("general.noMelting");
        this.iceDrop = fileConfiguration.getBoolean("general.iceDrop");
        this.noFreeze = fileConfiguration.getBoolean("general.noFreeze");
        this.iceWater = fileConfiguration.getBoolean("general.iceWater");
        this.designatedWorldName = fileConfiguration.getString("worlds.designatedWorldName");
        this.designatedWorldName2 = fileConfiguration.getString("worlds.designatedWorldName2");
        this.designatedWorldName3 = fileConfiguration.getString("worlds.designatedWorldName3");
        this.designatedWorldName4 = fileConfiguration.getString("worlds.designatedWorldName4");
        this.designatedWorldName5 = fileConfiguration.getString("worlds.designatedWorldName5");
        this.log.info("[NoMelt] Enabled");
        if (this.noMelting) {
            this.log.info("[NoMelt] - Melting is disabled.");
        } else {
            this.log.info("[NoMelt] - Melting is enabled.");
        }
        if (this.iceDrop) {
            this.log.info("[NoMelt] - Ice will drop");
        } else {
            this.log.info("[NoMelt] - Ice will not drop.");
        }
        if (this.noFreeze) {
            this.log.info("[NoMelt] - Freezing is Disabled.");
        } else {
            this.log.info("[NoMelt] - Freezing Enabled.");
        }
        if (!this.iceWater) {
            this.log.info("[NoMelt] - Ice will NOT turn to water upon breaking.");
        } else {
            this.log.info("[NoMelt] - Ice WILL turn into water upon breaking.");
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player)sender;
        if (commandLabel.equalsIgnoreCase("nomelt")) {
            player.sendMessage("You have found an Eagle Plugin :)");
            return true;
        }
        return false;
    }
}
