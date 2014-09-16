package pl.axxxon.micro.android.api.core;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import pl.axxxon.micro.android.log.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by mnarowski on 05.09.14.
 */
public abstract class AbstractApiCall {
    public static final int POST = 100;
    public static final int GET = 10;
    private ErrorHandler mErrorHandler;
    private ResponseHandler mResponseHandler;

    public static interface ResponseHandler<T>{
        void handle(T response);
    }

    public static interface ErrorHandler<T>{
        void handle(HttpResponse pHttpStatus,Object response,Exception cause);
    }

    public static class DefaultResponseHandler implements ResponseHandler<String>{
        private static final String TAG = "DefaultResponseHandler";

        @Override
        public void handle(String response) {
            Logger.d(TAG,response);
        }
    }

    public static class DefaultErrorHandler implements ErrorHandler<String>{
        private static final String TAG = "DefaultErrorHandler";

        @Override
        public void handle(HttpResponse pHttpStatus, Object response, Exception cause) {
            String message = TAG+" Log: \n";
            if(pHttpStatus!=null){
                message+= "Status Code: ["+pHttpStatus.getStatusLine().getStatusCode()+"]\n";
            }
            if(response!=null){
                message+="Response: ["+ response+"]\n";
            }
            if(cause != null){
                message += "Exception: ["+cause.toString()+"]\n";
            }
            Logger.d(TAG,message);
        }
    }

    public AbstractApiCall(){
        setResponseHandler(new DefaultResponseHandler());
        setErrorHandler(new DefaultErrorHandler());
    }

    public void execute(){
        makeApiCall(getUri(),getMethod(),getParams());
    }

    protected abstract List<NameValuePair> getParams();

    protected abstract int getMethod();

    protected abstract String getUri();

    public void setErrorHandler(ErrorHandler pErrorHandler){
        mErrorHandler = pErrorHandler;
    }

    public void setResponseHandler(ResponseHandler pResponseHandler){
        mResponseHandler = pResponseHandler;
    }

    private void makeApiCall(String pUrl,int method,List<NameValuePair> params){
        String response = null;
        // http client
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        try {

            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(pUrl);
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

            } else if (method == GET) {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    pUrl += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(pUrl);

                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            mResponseHandler.handle(response);
        } catch (UnsupportedEncodingException e) {
            mErrorHandler.handle(httpResponse,response,e);
        } catch (ClientProtocolException e) {
            mErrorHandler.handle(httpResponse,response,e);
        } catch (IOException e) {
            mErrorHandler.handle(httpResponse,response,e);
        }

    }
}
