package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trikh on 27-10-2016.
 */

public class Business extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int BUSINESS_LOADER_ID = 1;
    public final String url = "http://content.guardianapis.com/business?order-by=newest&api-key=7c1f0727-2bce-49e2-aa74-eb3a87f588db";
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private View rootView;

    public Business() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.news_list, container, false);
        TextView subject = (TextView) rootView.findViewById(R.id.subject);
        subject.setText(R.string.business);

        ListView newsListView = (ListView) rootView.findViewById(R.id.news_list);

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new NewsAdapter(getActivity(), new ArrayList<News>());
        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News currentNews = mAdapter.getItem(i);
                Uri link = Uri.parse(currentNews.getmUrl());
                Intent webLink = new Intent(Intent.ACTION_VIEW, link);
                startActivity(webLink);
            }
        });
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BUSINESS_LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new NewsLoader(getActivity(), url);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_news);
        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }


    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.clear();
    }
}
