package pl.axxxon.micro.android.entity;

import java.io.Serializable;

/**
 * Created by mnarowski on 30.08.14.
 */
public class User implements Serializable {

    private String mName;
    private String mSureName;
    private String mCity;
    private String mAge;


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

    public String getCity() {
        return mCity;
    }

    public void setCity(String pCity) {
        mCity = pCity;
    }

    public String getAge() {
        return mAge;
    }

    public void setAge(String pAge) {
        mAge = pAge;
    }
}
