package pl.axxxon.micro.android.ui.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by mnarowski on 31.08.14.
 */
public class SpriteDrawable extends Drawable {
    public static final String TAG = SpriteDrawable.class.getSimpleName();
    private final Bitmap mBitmap;
    private final int mFrameCount;
    private final int mFrameTime;
    private int mCurrentFrame;
    private final int mSpriteHeight;
    private final int mSpriteWidth;
    private final Rect mSourceRect;
    private long mFrameTicker;
    private int mX;
    private int mY;
    public SpriteDrawable(Bitmap pBitmap, int pFrameCount,int pFps){
        mBitmap = pBitmap;
        mFrameCount = pFrameCount;
        mFrameTime = 1000/pFps;
        mCurrentFrame = 0;
        mSpriteWidth = pBitmap.getWidth();
        mSpriteHeight = pBitmap.getHeight()/mFrameCount;
        mSourceRect = new Rect(0, 0, mSpriteWidth, mSpriteHeight);
        mFrameTicker = 0l;
    }
    public void update(long gameTime,Rect drawingRect) {
        if (gameTime > mFrameTicker + mFrameTime) {
            mFrameTicker = gameTime;
// increment the frame
            mCurrentFrame++;
            if (mCurrentFrame >= mFrameCount) {
                mCurrentFrame = 0;
            }
        }
        setX(drawingRect.width()/2-mSpriteWidth/2);
        setY(drawingRect.height()/2-mSpriteHeight/2);
        this.mSourceRect.top= mCurrentFrame* mSpriteHeight;
        this.mSourceRect.bottom = this.mSourceRect.top +mSpriteHeight;
    }
    @Override
    public void draw(final Canvas pCanvas) {
        Rect destRect = new Rect(getX(),getY(), getX() + mSpriteWidth, getY() + mSpriteHeight);
        pCanvas.drawBitmap(mBitmap, mSourceRect, destRect, null);
    }
    @Override
    public void setAlpha(final int i) {
    }
    @Override
    public void setColorFilter(final ColorFilter pColorFilter) {
    }
    @Override
    public int getOpacity() {
        return 0;
    }
    public int getX() {
        return mX;
    }
    public void setX(final int pX) {
        mX = pX;
    }
    public int getY() {
        return mY;
    }
    public void setY(final int pY) {
        mY = pY;
    }

        }
