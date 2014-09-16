package pl.axxxon.micro.android.ui.helpers;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mnarowski on 31.08.14.
 */
public class ViewHolder {
    private static Map<Integer,View> sViewMap = new HashMap<Integer, View>();
    public static View get(Integer id){
        return sViewMap.get(id);
    }

    public static void set(Integer id,View pView){
        sViewMap.put(id,pView);
    }

    public static View get(Integer id,Activity pContext){
        View result = null;
        result = get(id);
        if(result!=null){
            return result;
        }

        result=pContext.findViewById(id);
        set(id,result);
        return result;
    }
}
