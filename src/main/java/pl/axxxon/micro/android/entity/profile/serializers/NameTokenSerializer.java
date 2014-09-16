package pl.axxxon.micro.android.entity.profile.serializers;

import com.google.gson.*;
import pl.axxxon.micro.android.entity.profile.NameToken;

import java.lang.reflect.Type;

/**
 * Created by mnarowski on 05.09.14.
 */
public class NameTokenSerializer implements JsonSerializer<NameToken>,JsonDeserializer<NameToken> {
    @Override
    public NameToken deserialize(JsonElement pJsonElement, Type pType, JsonDeserializationContext pJsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(NameToken pNameToken, Type pType, JsonSerializationContext pJsonSerializationContext) {
        return null;
    }
}
