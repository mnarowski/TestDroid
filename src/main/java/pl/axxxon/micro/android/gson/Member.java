package pl.axxxon.micro.android.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mnarowski on 03.09.14.
 */
public class Member implements Serializable{
    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public String getSurename() {
        return mSurename;
    }

    public void setSurename(String pSurename) {
        mSurename = pSurename;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int pAge) {
        mAge = pAge;
    }

    @SerializedName("name")
    public String mName;
    @SerializedName("surename")
    public String mSurename;
    @SerializedName("age")
    public int mAge;
}
