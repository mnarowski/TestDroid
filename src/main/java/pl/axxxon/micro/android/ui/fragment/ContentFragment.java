package pl.axxxon.micro.android.ui.fragment;

import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.entity.profile.Profile;
import pl.axxxon.micro.android.gson.Parliment;
import pl.axxxon.micro.android.http.ProfileAdapter;
import pl.axxxon.micro.android.http.task.ProfileLoader;

/**
 * Created by mnarowski on 05.09.14.
 */
public class ContentFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Profile[]> {

    private ProfileAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Profile[]> onCreateLoader(int i, Bundle pBundle) {
        mAdapter = new ProfileAdapter(getActivity(),new Profile[]{});
        setListAdapter(mAdapter);
        return new ProfileLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Profile[]> pLoader, Profile[] pProfiles) {
        setListAdapter(mAdapter=new ProfileAdapter(getActivity(),pProfiles));
    }

    @Override
    public void onLoaderReset(Loader<Profile[]> pLoader) {

    }

}
