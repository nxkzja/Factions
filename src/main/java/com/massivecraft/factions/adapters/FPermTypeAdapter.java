package com.massivecraft.factions.adapters;

import java.lang.reflect.Type;

import com.massivecraft.factions.struct.FPerm;

import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonParseException;

public class FPermTypeAdapter implements JsonDeserializer<FPerm> {
    @Override
    public FPerm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return FPerm.parse(json.getAsString());
    }
}