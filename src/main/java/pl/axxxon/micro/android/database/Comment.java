package pl.axxxon.micro.android.database;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mnarowski on 03.09.14.
 */
public class Comment implements Serializable{

    private static final String EMPTY = "Empty comment";
    @Expose(serialize = true,deserialize = false)
    private int mId;

    @SerializedName("comment")
    private String mComment;

    public Comment(int pId,String pComment){
        mId = pId;
        mComment= pComment;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String pComment) {
        mComment = pComment;
    }

    @Override
    public String toString() {
        return mComment == null ? EMPTY : mComment;
    }
}
