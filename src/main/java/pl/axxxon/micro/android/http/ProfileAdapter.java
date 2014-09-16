package pl.axxxon.micro.android.http;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.entity.profile.Profile;
import pl.axxxon.micro.android.ui.helpers.ViewHolder;

import java.util.zip.Inflater;

/**
 * Created by mnarowski on 05.09.14.
 */
public class ProfileAdapter extends ArrayAdapter<Profile>{
    public ProfileAdapter(FragmentActivity pActivity, Profile[] objects) {
        super(pActivity, R.layout.profile_view,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderProfile holder = null;
        if(convertView == null || !(convertView.getTag() instanceof ViewHolderProfile)){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.profile_view,parent,false);
            holder = new ViewHolderProfile();
            ImageView view = (ImageView) convertView.findViewById(R.id.profile_data);
            holder.textView = view;
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolderProfile) convertView.getTag();
        }

//        holder.textView.setText(getItem(position).toString());
        ImageLoader.getInstance().displayImage(getItem(position).getUri(),holder.textView);

        return convertView;
    }

    public static class ViewHolderProfile{
        public ImageView textView;
    }
}
