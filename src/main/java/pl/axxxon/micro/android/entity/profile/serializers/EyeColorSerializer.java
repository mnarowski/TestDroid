package pl.axxxon.micro.android.entity.profile.serializers;

import com.google.gson.*;
import pl.axxxon.micro.android.entity.profile.EyeColor;

import java.lang.reflect.Type;

/**
 * Created by mnarowski on 05.09.14.
 */
public class EyeColorSerializer implements JsonDeserializer<EyeColor>,JsonSerializer<EyeColor> {
    @Override
    public EyeColor deserialize(JsonElement pJsonElement, Type pType, JsonDeserializationContext pJsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(EyeColor pEyeColor, Type pType, JsonSerializationContext pJsonSerializationContext) {
        return null;
    }
}
