package com.github.sharkzhs83.skill;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    Logger logger = getServer().getLogger();

    @Override
    public void onEnable() {
        logger.info(ChatColor.BLUE + "[Skill]스킬 플러그인 활성화");
    }

    @Override
    public void onDisable() {
        logger.info(ChatColor.RED + "[Skill]스킬 플러그인 비활성화");
    }
}
