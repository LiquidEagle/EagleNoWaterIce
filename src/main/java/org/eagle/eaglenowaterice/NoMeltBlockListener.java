package org.eagle.eaglenowaterice;


import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class NoMeltBlockListener implements Listener {
    private boolean noMelting;
    private boolean iceDrop;
    private boolean noFreeze;
    private boolean iceWater;
    private String designatedWorldName;
    private String designatedWorldName2;
    private String designatedWorldName3;
    private String designatedWorldName4;
    private String designatedWorldName5;
    private static EagleNoWaterIce plugin;

    public NoMeltBlockListener(EagleNoWaterIce paramNoMelt) {
        Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin) paramNoMelt);
        FileConfiguration fileConfiguration = paramNoMelt.getConfig();
        this.noMelting = fileConfiguration.getBoolean("general.noMelting");
        this.iceDrop = fileConfiguration.getBoolean("general.iceDrop");
        this.noFreeze = fileConfiguration.getBoolean("general.noFreeze");
        this.iceWater = fileConfiguration.getBoolean("general.iceWater");
        this.designatedWorldName = fileConfiguration.getString("worlds.designatedWorldName");
        this.designatedWorldName2 = fileConfiguration.getString("worlds.designatedWorldName2");
        this.designatedWorldName3 = fileConfiguration.getString("worlds.designatedWorldName3");
        this.designatedWorldName4 = fileConfiguration.getString("worlds.designatedWorldName4");
        this.designatedWorldName5 = fileConfiguration.getString("worlds.designatedWorldName5");
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent paramBlockFadeEvent) {
        if ((paramBlockFadeEvent.getBlock().getType() == Material.ICE || paramBlockFadeEvent.getBlock().getType() == Material.SNOW || paramBlockFadeEvent.getBlock().getType() == Material.SNOW_BLOCK) && this.noMelting) {
            paramBlockFadeEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent paramBlockFormEvent) {
        if (this.noFreeze) {
            paramBlockFormEvent.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent) {
        // Check if the event occurs in the designated world.
        if (paramBlockBreakEvent.getBlock().getType() == Material.ICE && !paramBlockBreakEvent.getBlock().getWorld().getName().equalsIgnoreCase(this.designatedWorldName) && (!paramBlockBreakEvent.getBlock().getWorld().getName().equalsIgnoreCase(this.designatedWorldName2)) && (!paramBlockBreakEvent.getBlock().getWorld().getName().equalsIgnoreCase(this.designatedWorldName3)) && (!paramBlockBreakEvent.getBlock().getWorld().getName().equalsIgnoreCase(this.designatedWorldName4)) && (!paramBlockBreakEvent.getBlock().getWorld().getName().equalsIgnoreCase(this.designatedWorldName5))) {
            System.out.println("User is in " + paramBlockBreakEvent.getBlock().getWorld().getName() + " and the enabled worlds are " + this.designatedWorldName + " & " + this.designatedWorldName2);
//            paramBlockBreakEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYou broke an ice block in world " + paramBlockBreakEvent.getBlock().getWorld().getName() + " and the enabled worlds are " + this.designatedWorldName + " & " + this.designatedWorldName2 + "!"));
            return; // If not, return and don't execute the plugin's functionality.
        }
//        if (!paramBlockBreakEvent.isCancelled() && paramBlockBreakEvent.getBlock().getType() == Material.ICE && this.iceDrop) {
//            ItemStack itemStack = new ItemStack(Material.ICE, 1);
//            paramBlockBreakEvent.getPlayer().getWorld().dropItemNaturally(paramBlockBreakEvent.getBlock().getLocation(), itemStack);
//        }
        if (!paramBlockBreakEvent.isCancelled() && !this.iceWater && paramBlockBreakEvent.getBlock().getType() == Material.ICE) {
            paramBlockBreakEvent.getBlock().setType(Material.AIR);
            System.out.println(paramBlockBreakEvent.getPlayer().getDisplayName() + " broke an ice block in world " + paramBlockBreakEvent.getBlock().getWorld().getName());
//            paramBlockBreakEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou broke an ice block in world " + paramBlockBreakEvent.getBlock().getWorld().getName()));
        }
    }
}
//    private boolean canBreakBlock(Block block, Player player) {
//        // Get the player associated with the world where the block is located
//        LocalPlayer localPlayer = getWorldGuardPlugin().wrapPlayer(player);
//
//        // Create a region query at the block's location
//        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
//        WorldGuardUtils.canBreakBlock()
//        // Check if the block's location is protected by any region with the flag "build"
//        ApplicableRegionSet regionSet = query.getApplicableRegions(block.getLocation().);
//        StateFlag.State state = regionSet.queryState(localPlayer, Flags.BUILD);
//        com.sk89q.worldedit.world.World world = localPlayer.getWorld();
//
//        Location loc = new Location(world,block.getLocation().getBlockX(),block.getLocation().getBlockY(),block.getLocation().getBlockZ());
//
//        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
//        RegionQuery query = container.createQuery();
//        ApplicableRegionSet set = query.getApplicableRegions(loc);
//
//        if (!WGBukkit.getRegionManager(event.getBlock().getWorld()).getApplicableRegions(BukkitUtil.toVector(event.getBlock().getLocation())).canBuild(WGBukkit.getPlugin().wrapPlayer(player)))
//
//        else {
//            return true;
//        }
//    }
//    private boolean isLocationWithinRegion(org.bukkit.Location location, CuboidRegion region) {
//        World world = location.getWorld();
//        if (world != null) {
//            // Check if the location's world matches the region's world
//            if (!world.getName().equals(region.getWorld().getName())) {
//                return false;
//            }
//
//            int x = location.getBlockX();
//            int y = location.getBlockY();
//            int z = location.getBlockZ();
//
//            // Check if the location is within the region's boundaries
//            if (x >= region.getXMin() && x <= region.getXMax() &&
//                    y >= region.getMinimumY() && y <= region.getYMax() &&
//                    z >= region.getZMin() && z <= region.getZMax()) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private WorldGuardPlugin getWorldGuardPlugin() {
//        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
//        if (plugin instanceof WorldGuardPlugin) {
//            return (WorldGuardPlugin) plugin;
//        } else {
//            // WorldGuard is not loaded or enabled
//            // Handle this case appropriately
//            return null;
//        }
//    }
//}