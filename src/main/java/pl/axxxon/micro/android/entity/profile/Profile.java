package pl.axxxon.micro.android.entity.profile;

import android.net.Uri;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by mnarowski on 05.09.14.
 */
public class Profile {
    @SerializedName("_id")
    private String mId;

    @SerializedName("index")
    private int mIndex;

    @SerializedName("guid")
    private String mGuid;

    @SerializedName("isActive")
    private boolean mIsActive;

    @SerializedName("balance")
    private String mBalance;

    @SerializedName("picture")
    private String mUri;

    @SerializedName("age")
    private int mAge;
    @SerializedName("eyeColor")
    private EyeColor mEyeColor;
    @SerializedName("name")
    private NameToken mName;

    @SerializedName("gender")
    private Gender mGender;

    @SerializedName("company")
    private String mCompany;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("registered")
    private String mRegistered;

    @SerializedName("about")
    private String mDescription;
    @SerializedName("latutide")
    private float mLatitude;
    @SerializedName("longitude")
    private float mLongitude;
    @SerializedName("tags")
    private String[] mTags;
    @SerializedName("friends")
    private Friend[] mFriends;

    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int pIndex) {
        mIndex = pIndex;
    }

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String pGuid) {
        mGuid = pGuid;
    }

    public boolean isActive() {
        return mIsActive;
    }

    public void setActive(boolean pIsActive) {
        mIsActive = pIsActive;
    }

    public String getBalance() {
        return mBalance;
    }

    public void setBalance(String pBalance) {
        mBalance = pBalance;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String pUri) {
        mUri = pUri;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int pAge) {
        mAge = pAge;
    }

    public EyeColor getEyeColor() {
        return mEyeColor;
    }

    public void setEyeColor(EyeColor pEyeColor) {
        mEyeColor = pEyeColor;
    }

    public NameToken getName() {
        return mName;
    }

    public void setName(NameToken pName) {
        mName = pName;
    }

    public Gender getGender() {
        return mGender;
    }

    public void setGender(Gender pGender) {
        mGender = pGender;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String pCompany) {
        mCompany = pCompany;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String pEmail) {
        mEmail = pEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String pPhone) {
        mPhone = pPhone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String pAddress) {
        mAddress = pAddress;
    }

    public String getRegistered() {
        return mRegistered;
    }

    public void setRegistered(String pRegistered) {
        mRegistered = pRegistered;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        mDescription = pDescription;
    }

    public float getLatitude() {
        return mLatitude;
    }

    public void setLatitude(float pLatitude) {
        mLatitude = pLatitude;
    }

    public float getLongitude() {
        return mLongitude;
    }

    public void setLongitude(float pLongitude) {
        mLongitude = pLongitude;
    }

    public String[] getTags() {
        return mTags;
    }

    public void setTags(String[] pTags) {
        mTags = pTags;
    }

    public Friend[] getFriends() {
        return mFriends;
    }

    public void setFriends(Friend[] pFriends) {
        mFriends = pFriends;
    }

    @Override
    public String toString() {
        return this.getAddress();
    }
}
