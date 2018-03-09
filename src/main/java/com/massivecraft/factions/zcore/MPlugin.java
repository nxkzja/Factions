package com.massivecraft.factions.zcore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.zcore.persist.EM;
import com.massivecraft.factions.zcore.persist.SaveTask;
import com.massivecraft.factions.zcore.util.LibLoader;
import com.massivecraft.factions.zcore.util.PermUtil;
import com.massivecraft.factions.zcore.util.Persist;
import com.massivecraft.factions.zcore.util.TL;
import com.massivecraft.factions.zcore.util.TextUtil;

import net.minecraft.util.com.google.common.reflect.TypeToken;
import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.GsonBuilder;


public abstract class MPlugin extends JavaPlugin {
    // Some utils
    public Persist persist;
    public TextUtil txt;
    public LibLoader lib;
    public PermUtil perm;

    // Persist related
    public Gson gson;
    private Integer saveTask = null;
    private boolean autoSave = true;
    protected boolean loadSuccessful = false;

    public String refCommand = "";

    public boolean getAutoSave() {
        return this.autoSave;
    }

    public void setAutoSave(boolean val) {
        this.autoSave = val;
    }

    // Listeners
    public MPluginSecretPlayerListener mPluginSecretPlayerListener;

    // -------------------------------------------- //
    // ENABLE
    // -------------------------------------------- //
    private long timeEnableStart;

    public boolean preEnable() {
        log("=== ENABLE START ===");
        timeEnableStart = System.currentTimeMillis();

        // Ensure basefolder exists!
        this.getDataFolder().mkdirs();

        // Create Utility Instances
        this.perm = new PermUtil(this);
        this.persist = new Persist(this);
        this.lib = new LibLoader(this);

        // GSON 2.1 is now embedded in CraftBukkit, used by the auto-updater: https://github.com/Bukkit/CraftBukkit/commit/0ed1d1fdbb1e0bc09a70bc7bfdf40c1de8411665
//		if ( ! lib.require("gson.jar", "http://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.1/gson-2.1.jar")) return false;
        this.gson = this.getGsonBuilder().create();

        this.txt = new TextUtil();
        initTXT();

        // attempt to get first command defined in plugin.yml as reference command, if any commands are defined in there
        // reference command will be used to prevent "unknown command" console messages
        try {
            Map<String, Map<String, Object>> refCmd = this.getDescription().getCommands();
            if (refCmd != null && !refCmd.isEmpty()) {
                this.refCommand = (String) (refCmd.keySet().toArray()[0]);
            }
        } catch (ClassCastException ex) {
        }

        // Create and register listeners
        this.mPluginSecretPlayerListener = new MPluginSecretPlayerListener(this);

        // Register recurring tasks
        if (saveTask == null && Conf.saveToFileEveryXMinutes > 0.0) {
            long saveTicks = (long) (20 * 60 * Conf.saveToFileEveryXMinutes); // Approximately every 30 min by default
            saveTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaveTask(this), saveTicks, saveTicks);
        }

        loadLang();

        loadSuccessful = true;
        return true;
    }

    public void postEnable() {
        log("=== ENABLE DONE (Took " + (System.currentTimeMillis() - timeEnableStart) + "ms) ===");
    }

    public void onDisable() {
        if (saveTask != null) {
            this.getServer().getScheduler().cancelTask(saveTask);
            saveTask = null;
        }
        // only save data if plugin actually loaded successfully
        if (loadSuccessful)
            EM.saveAllToDisc();
        log("Disabled");
    }

    public void suicide() {
        log("Now I suicide!");
        this.getServer().getPluginManager().disablePlugin(this);
    }

    // -------------------------------------------- //
    // Some inits...
    // You are supposed to override these in the plugin if you aren't satisfied with the defaults
    // The goal is that you always will be satisfied though.
    // -------------------------------------------- //

    public GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .serializeNulls()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE);
    }

    // -------------------------------------------- //
    // LANG AND TAGS
    // -------------------------------------------- //

    // These are not supposed to be used directly.
    // They are loaded and used through the TextUtil instance for the plugin.
    public Map<String, String> rawTags = new LinkedHashMap<String, String>();

    public void addRawTags() {
        this.rawTags.put("l", "<green>"); // logo
        this.rawTags.put("a", "<gold>"); // art
        this.rawTags.put("n", "<silver>"); // notice
        this.rawTags.put("i", "<yellow>"); // info
        this.rawTags.put("g", "<lime>"); // good
        this.rawTags.put("b", "<rose>"); // bad
        this.rawTags.put("h", "<pink>"); // highligh
        this.rawTags.put("c", "<aqua>"); // command
        this.rawTags.put("p", "<teal>"); // parameter
    }

    public void initTXT() {
        this.addRawTags();

        Type type = new TypeToken<Map<String, String>>() {
        }.getType();

        Map<String, String> tagsFromFile = this.persist.load(type, "tags");
        if (tagsFromFile != null) this.rawTags.putAll(tagsFromFile);
        this.persist.save(this.rawTags, "tags");

        for (Entry<String, String> rawTag : this.rawTags.entrySet()) {
            this.txt.tags.put(rawTag.getKey(), TextUtil.parseColor(rawTag.getValue()));
        }
    }

    private void loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        OutputStream out = null;
        InputStream defLangStream = this.getResource("lang.yml");
        if (!lang.exists()) {
            try {
                getDataFolder().mkdir();
                lang.createNewFile();
                if (defLangStream != null) {
                    out = new FileOutputStream(lang);
                    int read;
                    byte[] bytes = new byte[1024];

                    while ((read = defLangStream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defLangStream);
                    TL.setFile(defConfig);
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace(); // So they notice
                getLogger().severe("[Factions] Couldn't create language file.");
                getLogger().severe("[Factions] This is a fatal error. Now disabling");
                this.setEnabled(false); // Without it loaded, we can't send them messages
            } finally {
                if (defLangStream != null) {
                    try {
                        defLangStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for (TL item : TL.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }

        TL.setFile(conf);
        try {
            conf.save(lang);
        } catch (IOException e) {
            getLogger().log(Level.WARNING, "Factions: Failed to save lang.yml.");
            getLogger().log(Level.WARNING, "Factions: Report this stack trace to drtshock.");
            e.printStackTrace();
        }
    }

    // -------------------------------------------- //
    // HOOKS
    // -------------------------------------------- //
    public void preAutoSave() {

    }

    public void postAutoSave() {

    }

    // -------------------------------------------- //
    // LOGGING
    // -------------------------------------------- //
    public void log(Object msg) {
        log(Level.INFO, msg);
    }

    public void log(String str, Object... args) {
        log(Level.INFO, this.txt.parse(str, args));
    }

    public void log(Level level, String str, Object... args) {
        log(level, this.txt.parse(str, args));
    }

    public void log(Level level, Object msg) {
        Bukkit.getLogger().log(level, "[" + this.getDescription().getFullName() + "] " + msg);
    }
}
