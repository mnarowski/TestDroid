package pl.axxxon.micro.android.entity.profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mnarowski on 05.09.14.
 */
public class Friend {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private NameToken mNameToken;

    public Friend(int pId,NameToken pNameToken){
        mId = pId;
        mNameToken = pNameToken;
    }

    public NameToken getNameToken() {
        return mNameToken;
    }

    public void setNameToken(NameToken pNameToken) {
        mNameToken = pNameToken;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }
}
