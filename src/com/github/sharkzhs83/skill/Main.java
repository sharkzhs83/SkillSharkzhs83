package com.github.sharkzhs83.skill;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    //변수


    //기타
    Logger logger = getServer().getLogger();
    PluginManager pluginManager = Bukkit.getPluginManager();

    //서버 실행과 끝==============================================================================================================================================
    @Override
    public void onEnable() {
        logger.info(ChatColor.BLUE + "[Skill]스킬 플러그인 활성화");
        pluginManager.registerEvents(this,this);

        ShapedRecipe Golden_Apple = new ShapedRecipe(new ItemStack(Material.GOLDEN_APPLE, 3)).shape("!!!","!#!","!!!").setIngredient('!',Material.APPLE).setIngredient('#',Material.GOLD_INGOT);
        Bukkit.addRecipe(Golden_Apple);
    }

    @Override
    public void onDisable() {
        logger.info(ChatColor.RED + "[Skill]스킬 플러그인 비활성화");
    }

    //이벤트===================================================================================================================================================
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        ItemStack itemStack = new ItemStack(Material.APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GREEN + "LV1_힐링포션");
        itemMeta.setLore(Arrays.asList(ChatColor.BLUE + "체력을 스스로 20 으로 만듭니다"));
        itemStack.setItemMeta(itemMeta);

        if(action.equals(Action.LEFT_CLICK_AIR) && player.getItemInHand().equals(itemStack)) {
            player.setHealth(20.0);
            player.getInventory().removeItem(itemStack);
            player.sendMessage( ChatColor.AQUA + "포션사용!");
        }
    }

    //커맨드===================================================================================================================================================
    //종합명령어
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(label.equalsIgnoreCase("Skill")){
            if(sender.isOp()) {
                if(args.length == 0) {
                    player.sendMessage(ChatColor.RED + "잘못된 명령어형식 입니다!");
                }
                else if(args.length > 0) {
                    if(args[0].equalsIgnoreCase("item")) {
                        if(args[1].equalsIgnoreCase("LV1_heal_potion")) {
                            ItemStack itemStack = new ItemStack(Material.APPLE);
                            ItemMeta itemMeta = itemStack.getItemMeta();

                            itemMeta.setDisplayName(ChatColor.GREEN + "LV1_힐링포션");
                            itemMeta.setLore(Arrays.asList(ChatColor.BLUE + "체력을 스스로 20 으로 만듭니다"));
                            itemStack.setItemMeta(itemMeta);

                            player.getInventory().addItem(itemStack);

                            player.sendMessage( ChatColor.AQUA + "지급완료");
                        } else {
                            player.sendMessage( ChatColor.RED + "아이템이름이 잘못되었습니다!");
                        }
                    }
                }
            }
            //op가 아닐때
            else {
                player.sendMessage(ChatColor.RED + "권한이 없습니다!");
            }
        }

        return false;
    }
}
