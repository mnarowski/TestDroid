package pl.axxxon.micro.android.api;

import org.apache.http.NameValuePair;
import pl.axxxon.micro.android.api.core.AbstractApiCall;
import pl.axxxon.micro.android.entity.profile.Profile;

import java.util.List;

/**
 * Created by mnarowski on 05.09.14.
 */
public class ContentApiCall extends AbstractApiCall {
    private final String mUri;

    public ContentApiCall(String pUri,ICallback<Profile[]> callback) {
        super();
        mUri = pUri;
        setResponseHandler(new ContentApiResponseHandler(callback));

    }

    @Override
    protected List<NameValuePair> getParams() {
        return null;
    }

    @Override
    protected int getMethod() {
        return AbstractApiCall.GET;
    }

    @Override
    protected String getUri() {
        return mUri;
    }
}
