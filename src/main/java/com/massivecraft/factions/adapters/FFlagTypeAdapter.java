package com.massivecraft.factions.adapters;

import java.lang.reflect.Type;

import com.massivecraft.factions.struct.FFlag;

import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonParseException;

public class FFlagTypeAdapter implements JsonDeserializer<FFlag> {
    @Override
    public FFlag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return FFlag.parse(json.getAsString());
    }
}
