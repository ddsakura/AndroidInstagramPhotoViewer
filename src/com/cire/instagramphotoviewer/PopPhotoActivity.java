
package com.cire.instagramphotoviewer;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cire.instagramphotoviewer.modal.InstagramClient;
import com.cire.instagramphotoviewer.modal.InstagramClient.InstagramClientCallback;
import com.cire.instagramphotoviewer.modal.InstagramPhoto;

public class PopPhotoActivity extends Activity {

    private ArrayList<InstagramPhoto> mPhotos;
    private InstagramPhotoAdapter mPhotoAdapter;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_photo);

        spinner = (ProgressBar)findViewById(R.id.progressBar);

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchPopularPhotos();
                // fetchPopularPhotos();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mPhotos = new ArrayList<InstagramPhoto>();
        mPhotoAdapter = new InstagramPhotoAdapter(this, mPhotos);
        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(mPhotoAdapter);

        fetchPopularPhotos();
    }

    private void fetchPopularPhotos() {
        // swipeContainer.setRefreshing(true);

        InstagramClient insClient = new InstagramClient();
        insClient.getPopularMedia(new InstagramClientCallback() {

            @SuppressWarnings("unchecked")
            @Override
            public void onGetData(Object obj) {
                if (swipeContainer.getVisibility() != View.VISIBLE) {
                    swipeContainer.setVisibility(View.VISIBLE);
                }

                if (spinner.getVisibility() == View.VISIBLE) {
                    spinner.setVisibility(View.GONE);
                }

                mPhotos.clear();
                mPhotos.addAll((ArrayList<InstagramPhoto>)obj);
                mPhotoAdapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pop_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
