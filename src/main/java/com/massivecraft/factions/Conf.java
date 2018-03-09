package com.massivecraft.factions;

import com.massivecraft.factions.struct.FFlag;
import com.massivecraft.factions.struct.FPerm;
import com.massivecraft.factions.struct.Rel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.*;

public class Conf {
    public final static transient List<String> baseCommandAliases = new ArrayList<String>();

    // Colors
    public static ChatColor colorMember = ChatColor.GREEN;
    public static ChatColor colorAlly = ChatColor.DARK_PURPLE;
    public static ChatColor colorTruce = ChatColor.LIGHT_PURPLE;
    public static ChatColor colorNeutral = ChatColor.WHITE;
    public static ChatColor colorEnemy = ChatColor.RED;

    public static ChatColor colorNoPVP = ChatColor.GOLD;
    public static ChatColor colorFriendlyFire = ChatColor.DARK_RED;
    //public static ChatColor colorWilderness = ChatColor.DARK_GREEN;

    public static Map<FFlag, Boolean> factionFlagDefaults;
    public static Map<FFlag, Boolean> factionFlagIsChangeable;
    public static Map<FPerm, Set<Rel>> factionPermDefaults;
    public static Rel factionRankDefault = Rel.RECRUIT;

    // Power
    public static double powerPlayerMax = 10.0;
    public static double powerPlayerMin = -10.0;
    public static double powerPlayerStarting = 10.0; // New players start out with this power level
    public static double powerPerMinute = 0.2; // Default health rate... it takes 5 min to heal one power
    public static double powerPerDeath = 4.0; // A death makes you lose 4 power
    public static boolean scaleNegativePower = false; // Power regeneration rate increase as power decreases
    public static double scaleNegativeDivisor = 40.0; // Divisor for inverse power regeneration curve
    public static boolean powerRegenOffline = false;  // does player power regenerate even while they're offline?
    public static double powerOfflineLossPerDay = 0.0;  // players will lose this much power per day offline
    public static double powerOfflineLossLimit = 0.0;  // players will no longer lose power from being offline once their power drops to this amount or less
    public static double powerFactionMax = 0.0;  // if greater than 0, the cap on how much power a faction can have (additional power from players beyond that will act as a "buffer" of sorts)

    public static String prefixLeader = "**";
    public static String prefixOfficer = "*";
    public static String prefixMember = "+";
    public static String prefixRecruit = "-";

    public static int factionTagLengthMin = 3;
    public static int factionTagLengthMax = 10;
    public static boolean factionTagForceUpperCase = false;

    public static boolean newFactionsDefaultOpen = false;

    // when faction membership hits this limit, players will no longer be able to join using /f join; default is 0, no limit
    public static int factionMemberLimit = 0;

    // what faction ID to start new players in when they first join the server; default is 0, "no faction"
    public static String newPlayerStartingFactionID = "0";

    //public static boolean showMapFactionKey = true;
    //public static boolean showNeutralFactionsOnMap = true;
    //public static boolean showEnemyFactionsOnMap = true;

    // Disallow joining/leaving/kicking while power is negative
    public static boolean canLeaveWithNegativePower = true;

    // Configuration on the Faction tag in chat messages.
    public static boolean chatSetFormat = false;
    public static String chatSetFormatTo = "<{faction_relcolor}§l{faction_roleprefix}§r{faction_relcolor}{faction_tag_pr}" + ChatColor.WHITE.toString() + "%s> %s";
    public static boolean chatParseTags = true;
    public static boolean chatParseTagsColored = false;
    public static Map<String, String> chatSingleFormats = new HashMap<String, String>();
    public static transient boolean chatTagHandledByAnotherPlugin = false; // Why do we need this? (Olof asks)
    public static String chatTagFormat = "%s" + ChatColor.WHITE; // This one is almost deprecated now right? or is it?

    public static boolean broadcastDescriptionChanges = false;

    public static double saveToFileEveryXMinutes = 30.0;

    public static double autoLeaveAfterDaysOfInactivity = 10.0;
    public static double autoLeaveRoutineRunsEveryXMinutes = 5.0;
    public static int autoLeaveRoutineMaxMillisecondsPerTick = 5;  // 1 server tick is roughly 50ms, so default max 10% of a tick
    public static boolean removePlayerDataWhenBanned = true;

    public static boolean worldGuardChecking = false;

    //LWC
    public static boolean lwcIntegration = false;
    public static boolean onUnclaimResetLwcLocks = false;
    public static boolean onCaptureResetLwcLocks = false;

    // server logging options
    public static boolean logFactionCreate = true;
    public static boolean logFactionDisband = true;
    public static boolean logFactionJoin = true;
    public static boolean logFactionKick = true;
    public static boolean logFactionLeave = true;
    public static boolean logLandClaims = true;
    public static boolean logLandUnclaims = true;
    public static boolean logMoneyTransactions = true;
    public static boolean logPlayerCommands = true;

    // prevent some potential exploits
    public static boolean handleExploitObsidianGenerators = true;
    public static boolean handleExploitEnderPearlClipping = true;
    public static boolean handleExploitInteractionSpam = true;
    public static boolean handleExploitTNTWaterlog = false;

    public static boolean homesEnabled = true;
    public static boolean homesMustBeInClaimedTerritory = true;
    public static boolean homesTeleportToOnDeath = true;
    public static boolean homesRespawnFromNoPowerLossWorlds = true;
    public static boolean homesTeleportCommandEnabled = true;
    public static boolean homesTeleportCommandEssentialsIntegration = true;
    public static boolean homesTeleportCommandSmokeEffectEnabled = true;
    public static boolean homesTeleportAllowedFromEnemyTerritory = true;
    public static boolean homesTeleportAllowedFromDifferentWorld = true;
    public static double homesTeleportAllowedEnemyDistance = 32.0;
    public static boolean homesTeleportIgnoreEnemiesIfInOwnTerritory = true;


    public static Rel friendlyFireFromRel = Rel.TRUCE;
    public static boolean disablePVPForFactionlessPlayers = false;
    public static boolean enablePVPAgainstFactionlessInAttackersLand = false;

    public static int noPVPDamageToOthersForXSecondsAfterLogin = 3;

    //public static boolean peacefulMembersDisablePowerLoss = true;

    public static boolean permanentFactionsDisableLeaderPromotion = false;

    public static boolean claimsMustBeConnected = false;
    public static boolean claimingFromOthersAllowed = true;
    public static boolean claimsCanBeUnconnectedIfOwnedByOtherFaction = true;
    public static int claimsRequireMinFactionMembers = 1;
    public static int claimedLandsMax = 0;

    // if someone is doing a radius claim and the process fails to claim land this many times in a row, it will exit
    public static int radiusClaimFailureLimit = 9;

    //public static double considerFactionsReallyOfflineAfterXMinutes = 0.0;

    public static boolean protectOfflineFactionsFromExplosions = false;
    public static double offlineExplosionProtectionDelay = 2.0;

    public static int actionDeniedPainAmount = 2;

    // commands which will be prevented if the player is a member of a permanent faction
    public static Set<String> permanentFactionMemberDenyCommands = new LinkedHashSet<String>();

    // commands which will be prevented when in claimed territory of another faction
    public static Set<String> territoryNeutralDenyCommands = new LinkedHashSet<String>();
    public static Set<String> territoryEnemyDenyCommands = new LinkedHashSet<String>();

    public static double territoryShieldFactor = 0.3;

    // for claimed areas where further faction-member ownership can be defined

    public static boolean pistonProtectionThroughDenyBuild = true;

    public final transient static Set<Material> materialsEditOnInteract = EnumSet.noneOf(Material.class);
    public final transient static Set<Material> materialsEditTools = EnumSet.noneOf(Material.class);
    public final transient static Set<Material> materialsDoor = EnumSet.noneOf(Material.class);
    public final transient static Set<Material> materialsContainer = EnumSet.noneOf(Material.class);

    //public static Set<Material> territoryProtectedMaterialsWhenOffline = EnumSet.noneOf(Material.class);
    //public static Set<Material> territoryDenyUseageMaterialsWhenOffline = EnumSet.noneOf(Material.class);

    // TODO: Rename to monsterCreatureTypes
    public static transient Set<EntityType> monsters = EnumSet.noneOf(EntityType.class);
    
    // Economy settings
    public static boolean econEnabled = false;
    public static String econUniverseAccount = "";
    public static double econCostClaimWilderness = 30.0;
    public static double econCostClaimFromFactionBonus = 30.0;
    public static double econClaimAdditionalMultiplier = 0.5;
    public static double econClaimRefundMultiplier = 0.7;
    public static double econClaimUnconnectedFee = 0.0;
    public static double econCostCreate = 100.0;
    public static double econCostSethome = 30.0;
    public static double econCostJoin = 0.0;
    public static double econCostLeave = 0.0;
    public static double econCostKick = 0.0;
    public static double econCostInvite = 0.0;
    public static double econCostHome = 0.0;
    public static double econCostTag = 0.0;
    public static double econCostDesc = 0.0;
    public static double econCostTitle = 0.0;
    public static double econCostList = 0.0;
    public static double econCostMap = 0.0;
    public static double econCostPower = 0.0;
    public static double econCostShow = 0.0;
    public static double econCostOpen = 0.0;
    public static double econCostAlly = 0.0;
    public static double econCostTruce = 0.0;
    public static double econCostNeutral = 0.0;
    public static double econCostEnemy = 0.0;

    public static int econLandRewardTaskRunsEveryXMinutes = 20;
    public static double econLandReward = 0.00;

    //Faction banks, to pay for land claiming and other costs instead of individuals paying for them
    public static boolean bankEnabled = true;
    //public static boolean bankMembersCanWithdraw = false; //Have to be at least moderator to withdraw or pay money to another faction
    public static boolean bankFactionPaysCosts = true; //The faction pays for faction command costs, such as sethome
    public static boolean bankFactionPaysLandCosts = true; //The faction pays for land claiming costs.

    // mainly for other plugins/mods that use a fake player to take actions, which shouldn't be subject to our protections
    public static Set<String> playersWhoBypassAllProtection = new LinkedHashSet<String>();

    public static Set<String> worldsNoClaiming = new LinkedHashSet<String>();
    public static Set<String> worldsNoPowerLoss = new LinkedHashSet<String>();
    public static Set<String> worldsIgnorePvP = new LinkedHashSet<String>();
    // TODO: A better solution Would be to have One wilderness faction per world.
    //public static Set<String> worldsNoWildernessProtection = new LinkedHashSet<String>();

    public static transient int mapHeight = 8;
    public static transient int mapWidth = 39;
    public static transient char[] mapKeyChrs = "\\/#?$%=&^ABCDEFGHJKLMNOPQRSTUVWXYZ1234567890abcdeghjmnopqrsuvwxyz".toCharArray();

    static {
        baseCommandAliases.add("f");

        factionFlagDefaults = new LinkedHashMap<FFlag, Boolean>();
        for (FFlag flag : FFlag.values()) {
            factionFlagDefaults.put(flag, flag.defaultDefaultValue);
        }

        factionPermDefaults = new LinkedHashMap<FPerm, Set<Rel>>();
        for (FPerm perm : FPerm.values()) {
            factionPermDefaults.put(perm, perm.defaultDefaultValue);
        }

        chatSingleFormats.put("pl", " %s");
        chatSingleFormats.put("pr", "%s ");
        chatSingleFormats.put("pb", " %s ");

        territoryEnemyDenyCommands.add("home");
        territoryEnemyDenyCommands.add("sethome");
        territoryEnemyDenyCommands.add("spawn");
        territoryEnemyDenyCommands.add("tpahere");
        territoryEnemyDenyCommands.add("tpaccept");
        territoryEnemyDenyCommands.add("tpa");
        territoryEnemyDenyCommands.add("warp");

        materialsContainer.add(Material.DISPENSER);
        materialsContainer.add(Material.CHEST);
        materialsContainer.add(Material.FURNACE);
        materialsContainer.add(Material.BURNING_FURNACE);
        materialsContainer.add(Material.JUKEBOX);
        materialsContainer.add(Material.BREWING_STAND);
        materialsContainer.add(Material.ENCHANTMENT_TABLE);
        materialsContainer.add(Material.ANVIL);
        materialsContainer.add(Material.BEACON);
        materialsContainer.add(Material.TRAPPED_CHEST);
        materialsContainer.add(Material.DROPPER);
        materialsContainer.add(Material.HOPPER);

        materialsEditOnInteract.add(Material.DIODE_BLOCK_OFF);
        materialsEditOnInteract.add(Material.DIODE_BLOCK_ON);
        materialsEditOnInteract.add(Material.NOTE_BLOCK);
        materialsEditOnInteract.add(Material.CAULDRON);
        materialsEditOnInteract.add(Material.SOIL);

        materialsDoor.add(Material.WOODEN_DOOR);
        materialsDoor.add(Material.TRAP_DOOR);
        materialsDoor.add(Material.FENCE_GATE);

        materialsEditTools.add(Material.FIREBALL);
        materialsEditTools.add(Material.FLINT_AND_STEEL);
        materialsEditTools.add(Material.BUCKET);
        materialsEditTools.add(Material.WATER_BUCKET);
        materialsEditTools.add(Material.LAVA_BUCKET);

        monsters.add(EntityType.BLAZE);
        monsters.add(EntityType.CAVE_SPIDER);
        monsters.add(EntityType.CREEPER);
        monsters.add(EntityType.ENDERMAN);
        monsters.add(EntityType.ENDER_DRAGON);
        monsters.add(EntityType.GHAST);
        monsters.add(EntityType.GIANT);
        monsters.add(EntityType.MAGMA_CUBE);
        monsters.add(EntityType.PIG_ZOMBIE);
        monsters.add(EntityType.SILVERFISH);
        monsters.add(EntityType.SKELETON);
        monsters.add(EntityType.SLIME);
        monsters.add(EntityType.SPIDER);
        monsters.add(EntityType.WITCH);
        monsters.add(EntityType.WITHER);
        monsters.add(EntityType.ZOMBIE);
    }

    // -------------------------------------------- //
    // Persistance
    // -------------------------------------------- //
    private static transient Conf i = new Conf();

    public static void load() {
        P.p.persist.loadOrSaveDefault(i, Conf.class, "conf");
    }

    public static void save() {
        P.p.persist.save(i);
    }
}

