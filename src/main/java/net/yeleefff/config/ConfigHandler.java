package net.yeleefff.config;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import net.yeleefff.Moonphase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ConfigHandler {
    private static ConfigState LOADED_STATE;
    private static Path PATH;

    public static ConfigState getState() {
        return LOADED_STATE;
    }

    public static void load(Path dir) {
        PATH = dir.resolve("moon_phase.json");

        if (!Files.isRegularFile(PATH)) save(LOADED_STATE = new ConfigState(false, "full moon"));

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            JsonElement json = JsonParser.parseReader(reader);
            Optional<ConfigState> state = ConfigState.CODEC.parse(JsonOps.INSTANCE, json).result();

            if (state.isPresent()) {
                LOADED_STATE = state.get();
            } else {
                throw new JsonParseException("Invalid codec: An error occurred while attempting to load Moon Phase's config codec");
            }

        } catch (JsonParseException e) {
            Moonphase.LOGGER.error("Unable to parse config file; defaulting to base config");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        save(LOADED_STATE);
    }

    public static void save(ConfigState state) {
        try (BufferedWriter writer = Files.newBufferedWriter(PATH)) {
            JsonElement json = ConfigState.CODEC.encodeStart(JsonOps.INSTANCE, state).result().get();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(json));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
