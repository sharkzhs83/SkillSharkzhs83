package com.github.sharkzhs83.skill;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    //기타
    Logger logger = getServer().getLogger();
    PluginManager pluginManager = Bukkit.getPluginManager();

    PluginDescriptionFile pluginDescriptionFile = this.getDescription();



    //서버 실행과 끝==============================================================================================================================================
    @Override
    public void onEnable() {
        logger.info(ChatColor.BLUE + pluginDescriptionFile.getName() + ChatColor.RED + " v" + pluginDescriptionFile.getVersion() + ChatColor.YELLOW +" [활성화]");
        pluginManager.registerEvents(this,this);

        ItemStack jumping_meet = new ItemStack(Material.COOKED_BEEF);
        ItemMeta jumping_meet_meta = jumping_meet.getItemMeta();

        jumping_meet_meta.setDisplayName(ChatColor.RED + "점프강화의 고기");
        jumping_meet_meta.setLore(Arrays.asList(ChatColor.GREEN + "일시적 점프강화"));
        jumping_meet.setItemMeta(jumping_meet_meta);

        ShapedRecipe jumping_meet_recipe = new ShapedRecipe(new ItemStack(jumping_meet)).shape(" ! ", " @ ", "   ").setIngredient('!', Material.RABBIT_FOOT).setIngredient('@',Material.COOKED_BEEF);
        Bukkit.addRecipe(jumping_meet_recipe);

    }

    @Override
    public void onDisable() {
        logger.info(ChatColor.BLUE + pluginDescriptionFile.getName() + ChatColor.RED + " v" + pluginDescriptionFile.getVersion() + ChatColor.YELLOW +" [비활성화]");
    }

    //이벤트===================================================================================================================================================
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        ItemStack LV1_heal = new ItemStack(Material.APPLE);
        ItemMeta LV1_heal_meta = LV1_heal.getItemMeta();

        LV1_heal_meta.setDisplayName(ChatColor.GREEN + "LV1_힐링포션");
        LV1_heal_meta.setLore(Arrays.asList(ChatColor.BLUE + "체력과마나를 스스로 20 으로 만듭니다"));
        LV1_heal.setItemMeta(LV1_heal_meta);

        if(action.equals(Action.LEFT_CLICK_AIR) && player.getItemInHand().equals(LV1_heal)) {
            player.setHealth(20.0);
            player.setFoodLevel(20);
            player.getInventory().removeItem(LV1_heal);
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
                            ItemStack LV1_heal = new ItemStack(Material.APPLE);
                            ItemMeta LV1_heal_meta = LV1_heal.getItemMeta();

                            LV1_heal_meta.setDisplayName(ChatColor.GREEN + "LV1_힐링포션");
                            LV1_heal_meta.setLore(Arrays.asList(ChatColor.BLUE + "체력과마나를 스스로 20 으로 만듭니다"));
                            LV1_heal.setItemMeta(LV1_heal_meta);

                            player.getInventory().addItem(LV1_heal);

                            player.sendMessage( ChatColor.AQUA + "지급완료");
                        }
                        if(args[1].equalsIgnoreCase("firewand")) {
                            ItemStack FireBallWand = new ItemStack(Material.BLAZE_ROD);
                            ItemMeta FireBallMeta = FireBallWand.getItemMeta();

                            FireBallMeta.setDisplayName(ChatColor.RED + "화염지팡이");
                            FireBallMeta.setLore(Arrays.asList(ChatColor.BLUE + "파이어볼을 직선으로 발사합니다"));
                            FireBallWand.setItemMeta(FireBallMeta);

                            player.getInventory().addItem(FireBallWand);

                            player.sendMessage( ChatColor.AQUA + "지급완료");
                        }
                        if(args[1].equalsIgnoreCase("tanker_sword")) {
                            ItemStack tanker_sword = new ItemStack(Material.GOLDEN_SWORD);
                            ItemMeta tanker_sword_meta = tanker_sword.getItemMeta();

                            tanker_sword_meta.setDisplayName(ChatColor.BLUE + "평범하진 않은 금검");
                            tanker_sword_meta.setLore(Arrays.asList(ChatColor.AQUA + "여러가지 버프를 받습니다"));
                            tanker_sword.setItemMeta(tanker_sword_meta);

                            player.getInventory().addItem(tanker_sword);

                            player.sendMessage( ChatColor.AQUA + "지급완료");
                        }
                        if(args[1].equalsIgnoreCase("jumping_meet")) {
                            ItemStack jumping_meet = new ItemStack(Material.COOKED_BEEF);
                            ItemMeta jumping_meet_meta = jumping_meet.getItemMeta();

                            jumping_meet_meta.setDisplayName(ChatColor.RED + "점프강화의 고기");
                            jumping_meet_meta.setLore(Arrays.asList(ChatColor.GREEN + "일시적 점프강화"));
                            jumping_meet.setItemMeta(jumping_meet_meta);

                            player.getInventory().addItem(jumping_meet);

                            player.sendMessage( ChatColor.AQUA + "지급완료");
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

    //스킬========================================================================================================================================================
    @EventHandler
    public void FireBall(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        ItemStack FireBallWand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta FireBallMeta = FireBallWand.getItemMeta();

        FireBallMeta.setDisplayName(ChatColor.RED + "화염지팡이");
        FireBallMeta.setLore(Arrays.asList(ChatColor.BLUE + "파이어볼을 직선으로 발사합니다"));
        FireBallWand.setItemMeta(FireBallMeta);

        int mana = player.getFoodLevel();


        if(action == Action.LEFT_CLICK_AIR) {
            if(player.getItemInHand().equals(FireBallWand)) {
                if (mana == 0) {
                    player.sendActionBar(ChatColor.RED + "마나부족!");
                    player.spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 5);
                    player.getLocation().createExplosion(0);
                }
                else {
                    player.spawnParticle(Particle.FLAME, player.getLocation(), 5000);
                    player.launchProjectile(Fireball.class);
                    player.sendActionBar(ChatColor.AQUA + "파이어볼 사용!");
                    player.setFoodLevel(mana - 4);
                }
            }
        }
    }
    @EventHandler
    public void TankerSword(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        ItemStack tanker_sword = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta tanker_sword_meta = tanker_sword.getItemMeta();

        tanker_sword_meta.setDisplayName(ChatColor.BLUE + "평범하진 않은 금검");
        tanker_sword_meta.setLore(Arrays.asList(ChatColor.AQUA + "여러가지 버프를 받습니다"));
        tanker_sword.setItemMeta(tanker_sword_meta);

        int mana = player.getFoodLevel();

        if(action == Action.LEFT_CLICK_AIR) {
            if(player.getItemInHand().equals(tanker_sword)) {
                if (mana < 20) {
                    player.sendActionBar(ChatColor.RED + "마나부족!");
                    player.spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 5);
                    player.getLocation().createExplosion(0);
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20*50, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20*30, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*16, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*20, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*2, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*14, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*2, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*15, 2));

                    player.sendActionBar(ChatColor.AQUA + "버프사용(디버프 포함)");

                    player.setFoodLevel(mana -20);
                }
            }
        }
    }
    @EventHandler
    public void JumpingMeet(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        ItemStack jumping_meet = new ItemStack(Material.COOKED_BEEF);
        ItemMeta jumping_meet_meta = jumping_meet.getItemMeta();

        jumping_meet_meta.setDisplayName(ChatColor.RED + "점프강화의 고기");
        jumping_meet_meta.setLore(Arrays.asList(ChatColor.GREEN + "일시적 점프강화"));
        jumping_meet.setItemMeta(jumping_meet_meta);

        if(action == Action.LEFT_CLICK_AIR) {
            if(player.getItemInHand().equals(jumping_meet)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20*5, 4));
                player.sendTitle(ChatColor.GREEN + "점프강화",ChatColor.AQUA + "5초간의 행복");
                player.getInventory().removeItem(jumping_meet);
            }
        }
    }
}
