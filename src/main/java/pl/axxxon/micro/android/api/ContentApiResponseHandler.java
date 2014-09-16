package pl.axxxon.micro.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.axxxon.micro.android.api.core.AbstractApiCall;
import pl.axxxon.micro.android.entity.profile.*;
import pl.axxxon.micro.android.entity.profile.serializers.EyeColorSerializer;
import pl.axxxon.micro.android.entity.profile.serializers.GenderSerializer;
import pl.axxxon.micro.android.entity.profile.serializers.NameTokenSerializer;

/**
 * Created by mnarowski on 05.09.14.
 */
public class ContentApiResponseHandler implements AbstractApiCall.ResponseHandler<String> {

    private ICallback mCallback;

    public ContentApiResponseHandler(ICallback<Profile[]> pCallback) {
        setCallback(pCallback);
    }

    public void setCallback(ICallback pCallback){
        mCallback = pCallback;
    }

    @Override
    public void handle(String response) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(NameToken.class,new NameTokenSerializer());
        builder.registerTypeAdapter(Gender.class,new GenderSerializer());
        builder.registerTypeAdapter(EyeColor.class,new EyeColorSerializer());
        Gson gson = builder.create();
        Profile[] profiles = gson.fromJson(response,Profile[].class);
        if(mCallback!=null){
            mCallback.send(profiles);
        }
    }

}
