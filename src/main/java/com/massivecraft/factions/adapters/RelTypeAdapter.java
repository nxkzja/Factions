package com.massivecraft.factions.adapters;

import java.lang.reflect.Type;

import com.massivecraft.factions.struct.Rel;

import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonParseException;

public class RelTypeAdapter implements JsonDeserializer<Rel> {
    @Override
    public Rel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Rel.parse(json.getAsString());
    }
}
