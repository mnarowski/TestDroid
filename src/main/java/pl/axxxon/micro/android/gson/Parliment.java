package pl.axxxon.micro.android.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mnarowski on 03.09.14.
 */
public class Parliment implements Serializable{
    @SerializedName("count")
    public int mMembersCount;

    public ArrayList<Member> getMembers() {
        return mMembers;
    }

    public void setMembers(ArrayList<Member> pMembers) {
        mMembers = pMembers;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String pCountry) {
        mCountry = pCountry;
    }

    public int getMembersCount() {
        return mMembers.size();
    }

    @SerializedName("country")
    public String mCountry;

    public ArrayList<Member> mMembers = new ArrayList<Member>();

    public void addMember(Member pMember){
        mMembers.add(pMember);
    }
}
