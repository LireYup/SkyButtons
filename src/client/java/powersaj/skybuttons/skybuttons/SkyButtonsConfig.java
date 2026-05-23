package powersaj.skybuttons.skybuttons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SkyButtonsConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type MAP_TYPE = new TypeToken<Map<String, Boolean>>() {}.getType();

    private static Path configFile;
    private static Map<String, Boolean> data;

    private SkyButtonsConfig() {}

    public static void load() {
        if (configFile == null) {
            configFile = FabricLoader.getInstance().getConfigDir().resolve("skybuttons.json");
        }
        data = new HashMap<>();

        if (Files.exists(configFile)) {
            try {
                var raw = Files.readString(configFile);
                Map<String, Boolean> loaded = GSON.fromJson(raw, MAP_TYPE);
                if (loaded != null) {
                    data.putAll(loaded);
                }
            } catch (IOException e) {
                // file corrupted or unreadable, start fresh
            }
        }
    }

    public static void save() {
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, GSON.toJson(data));
        } catch (IOException e) {
            // silently fail — config state lost until next save
        }
    }

    public static boolean get(String key, boolean defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    public static void set(String key, boolean value) {
        data.put(key, value);
        save();
    }
}
