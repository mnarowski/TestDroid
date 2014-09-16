package pl.axxxon.micro.android.entity.profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mnarowski on 05.09.14.
 */
public class NameToken {
    @SerializedName("name")
    private String mName;
    @SerializedName("surename")
    private String mSureName;

    public NameToken(String pName,String pSureName){
        mName = pName;
        mSureName = pSureName;
    }

    public String getSureName() {
        return mSureName;
    }

    public void setSureName(String pSureName) {
        mSureName = pSureName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }
}
