package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CompanionData {

    public static Random rand = new Random();

    public static Item[] ALL_FOODS = new Item[] {
//            Items.CAKE,
            Items.COOKIE,
            Items.BREAD,
            Items.PUMPKIN_PIE,
//            Items.GOLDEN_APPLE,
//            Items.ENCHANTED_GOLDEN_APPLE,
//            Items.MELON,
            Items.MELON_SLICE,
            Items.APPLE,
            Items.GLOW_BERRIES,
            Items.SWEET_BERRIES,
//            Items.CHORUS_FRUIT,
//            Items.GOLDEN_CARROT,
//            Items.BEETROOT,
            Items.CARROT,
            Items.POTATO,
            Items.BAKED_POTATO,
//            Items.DRIED_KELP,
//            Items.MUSHROOM_STEW,
//            Items.BEETROOT_SOUP,
            Items.COOKED_SALMON,
            Items.COOKED_COD,
            Items.SALMON,
            Items.COD,
            Items.TROPICAL_FISH,
            Items.COOKED_MUTTON,
            Items.COOKED_PORKCHOP,
            Items.COOKED_BEEF,
            Items.COOKED_CHICKEN,
            Items.COOKED_RABBIT,
//            Items.MUTTON,
//            Items.PORKCHOP,
//            Items.BEEF,
//            Items.CHICKEN,
//            Items.RABBIT,
//            Items.RABBIT_STEW
    };

    public static MutableComponent[] tameFail = new MutableComponent[]{
            Component.literal("我需要更多的食物。"),
            Component.literal("就这些吗？"),
            Component.literal("我还饿着呢。"),
            Component.literal("我还能再要一些吗？"),
            Component.literal("我需要再多一点。"),
            Component.literal("这还不够。"),
    };

    public static MutableComponent[] notTamed = new MutableComponent[]{
            Component.literal("你有食物吗？"),
            Component.literal("我饿了。"),
            Component.literal("你在这附近见过食物吗？"),
            Component.literal("我需要些食物。"),
            Component.literal("我希望我有些食物。"),
            Component.literal("我饿极了。"),
    };

    public static MutableComponent[] WRONG_FOOD = new MutableComponent[]{
            Component.literal("这不是我要求的。"),
            Component.literal("我没要求这个。"),
            Component.literal("看来你没理解我的请求。"),
            Component.literal("你忘了我要求什么了吗？"),
            Component.literal("我不记得我请求过这个")
    };

    public static MutableComponent[] ENOUGH_FOOD = new MutableComponent[]{
            Component.literal("我已经有足够的了。"),
            Component.literal("我不再想要那个了。"),
            Component.literal("我现在想要别的东西。"),
    };

    public static Class<?>[] alertMobs = new Class<?>[]{
            Blaze.class,
            EnderMan.class,
            Endermite.class,
            Ghast.class,
            Giant.class,
            Guardian.class,
            Hoglin.class,
            MagmaCube.class,
            Phantom.class,
            Shulker.class,
            Silverfish.class,
            Slime.class,
            Spider.class,
            Vex.class,
            AbstractSkeleton.class,
            Zoglin.class,
            Zombie.class,
            Raider.class
    };

    public static Class<?>[] huntMobs = new Class<?>[]{
            Chicken.class,
            Cow.class,
            MushroomCow.class,
            Pig.class,
            Rabbit.class,
            Sheep.class
    };

    // skins[0] == male, skins[1] == female
    public static ResourceLocation[][] skins = new ResourceLocation[][] {
            new ResourceLocation[] {
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-man-hugh.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/alexandros.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/cyrus.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/diokles.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/dion.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/georgios.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/ioannis.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-peasant-schwaechlich.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-peasant-without-vest.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-peasant-with-vest-on.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/panos.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/viking-blue-tunic.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/cronos-jojo.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-man-alard.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/peasant-ginger.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/townsman-green-tunic.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/polish-farmer.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/peasant.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/rustic-farmer.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-villager.png"),
            },
            new ResourceLocation[] {
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/a-rogue-i-guess.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/deidre-gramville.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/deidre-gramville2.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/eleora-halle.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/fantastic-blue.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/ftu-emma.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/girl-medieval-peasant.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/medieval-barmaid.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/runaway.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/shannon-flux.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/the-traveller.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/x-ayesha.png"),
            }
    };

    public static int getHealthModifier() {
        float healthFloat = rand.nextFloat();
        if (healthFloat <= 0.03) {
            return -4;
        } else if (healthFloat <= 0.1) {
            return -3;
        } else if (healthFloat <= 0.2) {
            return -2;
        } else if (healthFloat <= 0.35) {
            return -1;
        } else if (healthFloat <= 0.65) {
            return 0;
        } else if (healthFloat <= 0.8) {
            return 1;
        } else if (healthFloat <= 0.9) {
            return 2;
        } else if (healthFloat <= 0.97) {
            return 3;
        } else {
            return 4;
        }
    }

    public static ItemStack getSpawnArmor(EquipmentSlot armorType) {
        float materialFloat = rand.nextFloat();
        if (materialFloat <= 0.4F) {
            return ItemStack.EMPTY;
        } else if(materialFloat <= 0.70F) {
            switch(armorType) {
                case HEAD:
                    return Items.LEATHER_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.LEATHER_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.LEATHER_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.LEATHER_BOOTS.getDefaultInstance();
            }
        } else if(materialFloat <= 0.90F) {
            switch(armorType) {
                case HEAD:
                    return Items.CHAINMAIL_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.CHAINMAIL_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.CHAINMAIL_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.CHAINMAIL_BOOTS.getDefaultInstance();
            }
        } else {
            switch(armorType) {
                case HEAD:
                    return Items.IRON_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.IRON_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.IRON_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.IRON_BOOTS.getDefaultInstance();
            }
        }
        return ItemStack.EMPTY;
    }

    public static String getRandomName(int sex) {
        Random rand = new Random();
        String firstName = firstNames[sex][rand.nextInt(firstNames[sex].length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    // Names source: https://github.com/ironarachne/namegen/blob/main/swedishnames.go
    // firstNames[0] == male, firstNames[1] == female
    public static String[][] firstNames = new String[][]{
            new String[] {
                    "天辰", "迟林", "子威", "逸辰", "宇轩", "浩宇", "逸辰", "宇辰", "子墨",
                    "宇航", "浩然", "子浩", "逸辰", "明涛", "浩然", "俊杰", "子涵", "慧芬", "佳丽",
                    "佳豪", "子睿", "博文", "一鸣", "俊德", "浩宇", "浩洋", "永乐", "雨泽", "佳伟", "小丹",
                    "伟国", "明宇", "子鑫", "世磊", "志鹏", "若轩", "木阳", "乐洋", "博成", "建宇", "天阔",
                    "东洋", "瑶瑶", "佩佩", "思成", "佳妮", "佳欣", "宇轩", "逸林", "小丽",
                    "乐健", "凯宇", "伟涵", "成伟", "瑞杰", "翔浩", "泽军", "宇恒", "立轩", "成宇",
                    "景宇", "晨宇", "泽洋", "乐群", "志远", "家源", "朝旭", "文涛", "朝军", "振宇",
                    "宇轩", "志强", "泽林", "文轩", "天翔", "宇文", "思淼", "智宇", "昊天", "志宸",
                    "子轩", "思远", "子杰", "天赋", "文杰", "昊宇", "志泽", "天佑", "子豪", "宇航",
                    "智翔", "昊然", "天宇", "宇星", "泽宇", "昊云", "子骁", "文博", "天宏", "宇晗"
            },
            new String[] {
                    "依诺", "心怡", "子涵", "雨彤", "欣燕", "可欣", "宇希", "雨彤", "梦瑶", "婵娟",
                    "春华", "冬梅", "惠英", "慧芬", "佳丽", "佳怡", "佳慧", "兰芬", "丽华", "丽梅",
                    "丽娜", "美莲", "明霞", "明珠", "巧慧", "小丹", "晓慧", "玉明", "诗云", "美琪",
                    "伊彤", "若轩", "欣月", "燕娅", "乐齐", "思宇", "晴艺", "依然", "紫莹", "紫威", "英雯",
                    "文欣", "玉芹", "佩珍", "盈月", "之若", "王淑", "陌愁", "雨涵", "小琪", "未茗", 
                    "艳琴", "霜霜", "飞飞", "艳然", "洛琪", "小彤", "萌可", "颖姿", "凌怡", "月瀚",
                    "小露", "瑞晋", "亮亮", "美荣", "珊珊", "倩倩", "小蓉", "天艺", "文倩", "慧音",
                    "芷若", "思琪", "婷婷", "春燕", "雨婷", "怡宁", "悦心", "雪莲", "梦璐", "佳怡",
                    "欣妍", "佳莉", "雨欣", "梦洁", "欣悦", "丽萍", "珊珊", "静宜", "雨婷", "悦怡",
                    "嘉琪", "佳妮", "雨欣", "瑶瑶", "悦心", "丽娜", "宛儿", "婷婷", "瑶瑶", "静宜"
            }

    };

    public static String[] lastNames = new String[] {
            "李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴", "蔡", "邰", "楪", 
            "程", "林", "陆", "夏", "谢", "田", "苏", "廖", "周", "郑", "徐", "马", "陶", "龙", "常", "黄",
            "高", "方", "白", "谭", "邝", "魏", "申", "吴", "段", "熊", "彭", "陆", "关", "曹", "冯", "袁", "石",
            "孙", "黎", "周", "吕", "韩", "曾", "许", "尤", "施", "严", "金", "邹", "贾", "夏", "甘", "卢", "姜"
    };
}
