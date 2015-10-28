package com.yahoo.myapp.socialphoto;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yahoo.myapp.socialphoto.client.InstagramApi;

import org.scribe.builder.api.Api;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 *
 */
public class InstagramClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = InstagramApi.class;
    public static final String REST_URL = "https://api.instagram.com/v1"; // base API URL

    public static final String REST_CLIENT_ID = "edfd2903b8074ac19f03fa25760968c6";
    public static final String REST_CLIENT_SECRET = "859e3dfe9c00443baa671586e3bde869";

    public static final String REST_CALLBACK_URL = "oauth://instaclient.com";
    public static final String GET_HOME_TIMELINE = "statuses/home_timeline.json";
    public static final String GET_MENTIONS_TIMELINE = "statuses/mentions_timeline.json";
    public static final String POST_TWEET = "statuses/update.json";
    public static final String MY_PROFILE = "account/verify_credentials.json";
    public static final String USER_TIMELINE = "statuses/user_timeline.json";
    public static final String USER_PROFILE = "users/show.json";
    
    public InstagramClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CLIENT_ID, REST_CLIENT_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(long maxid, int count, long sinceid, AsyncHttpResponseHandler handler) {
    	getTimeline(getApiUrl(GET_HOME_TIMELINE), maxid, count, sinceid, handler);
    }
    
    public void getMentionsTimeline(long maxid, int count, long sinceid, AsyncHttpResponseHandler handler) {
    	getTimeline(getApiUrl(GET_MENTIONS_TIMELINE), maxid, count, sinceid, handler);
    }
    
    public void getTimeline(String apiUrl, long maxid, int count, long sinceid, AsyncHttpResponseHandler handler) {
    	RequestParams params = new RequestParams();
    	if (maxid > 0)
    		params.put("max_id", maxid + "");
    	params.put("count", count + "");
    	if (sinceid > 0)
    		params.put("since_id", sinceid + "");
    	else
    		params.put("since_id", "1");
    	
    	Log.w("Twitter", params.toString() + " ");
    	client.get(apiUrl, params, handler); //pass null for params if no params
    }
    
    public void getUserTimeline(AsyncHttpResponseHandler handler) {
    	client.get(getApiUrl(USER_TIMELINE), handler);
    }
    
    public void getUserTimeline(long userId, AsyncHttpResponseHandler handler) {
    	String apiUrl = getApiUrl(USER_TIMELINE);
    	RequestParams params = new RequestParams();
    	params.put("user_id", userId + "");
    	client.get(apiUrl, params, handler);
    }
    
    public void getProfileInfo(AsyncHttpResponseHandler handler) {
    	client.get(getApiUrl(MY_PROFILE), handler);
    }
    
    public void getProfileInfo(long userId, AsyncHttpResponseHandler handler) {
    	String apiUrl = getApiUrl(USER_PROFILE);
    	RequestParams params = new RequestParams();
    	params.put("user_id", userId + "");
    	client.get(apiUrl, params, handler);
    }
    
    public void postTweet(String tweetText) {
    	String apiUrl = getApiUrl(POST_TWEET);
    	RequestParams params = new RequestParams();
    	params.put("status", tweetText);
    	client.post(apiUrl, params, new AsyncHttpResponseHandler());
    }
    
}
