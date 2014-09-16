package pl.axxxon.micro.android.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import pl.axxxon.micro.android.R;
import pl.axxxon.micro.android.database.columns.CommentsTableDefinition;
import pl.axxxon.micro.android.providers.CommentsProvider;

/**
 * Created by mnarowski on 03.09.14.
 */
public class CommentsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),R.layout.cursorlistitem,null,new String[]{CommentsTableDefinition.COMMENT},new int[]{R.id.label});
        getLoaderManager().initLoader(0,null,this);
        mAdapter = adapter;
        setListAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle pBundle) {
        String[] projection = new String[]{CommentsTableDefinition._ID,CommentsTableDefinition.COMMENT};
        CursorLoader loader = new CursorLoader(getActivity(), CommentsProvider.CONTENT_URI,projection,null,null,null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> pCursorLoader, Cursor pCursor) {
        mAdapter.swapCursor(pCursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> pCursorLoader) {
        mAdapter.swapCursor(null);
    }
}