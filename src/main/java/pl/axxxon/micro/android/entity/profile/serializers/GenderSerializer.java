package pl.axxxon.micro.android.entity.profile.serializers;

import com.google.gson.*;
import pl.axxxon.micro.android.entity.profile.Gender;

import java.lang.reflect.Type;

/**
 * Created by mnarowski on 05.09.14.
 */
public class GenderSerializer implements JsonSerializer<Gender>,JsonDeserializer<Gender> {
    @Override
    public Gender deserialize(JsonElement pJsonElement, Type pType, JsonDeserializationContext pJsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(Gender pGender, Type pType, JsonSerializationContext pJsonSerializationContext) {
        return null;
    }
}
