
package com.cire.instagramphotoviewer.modal;

import java.util.ArrayList;

import org.apache.http.Header;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class InstagramClient {
    private static final String CLIENT_ID = "f02e027b37ff46369146cc56822d7808";
    private static final String BASE_URL = "https://api.instagram.com/v1/";

    public interface InstagramClientCallback {
        public void onGetData(Object obj);
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl + "?client_id=" + CLIENT_ID;
    }

    public void getPopularMedia(final InstagramClientCallback instagramClientCallback) {

        InstagramClient.get("media/popular", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                ArrayList<InstagramPhoto> mPhotos = new ArrayList<InstagramPhoto>();
                try {
                    JSONArray photosJSON = response.getJSONArray("data");
                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = mapper.readValue(photoJSON.toString(), InstagramPhoto.class);
                        mPhotos.add(photo);
                    }
                    instagramClientCallback.onGetData(mPhotos);
                } catch (Exception e) {
                    instagramClientCallback.onGetData(null);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                instagramClientCallback.onGetData(null);
            }
        });
    }
}
