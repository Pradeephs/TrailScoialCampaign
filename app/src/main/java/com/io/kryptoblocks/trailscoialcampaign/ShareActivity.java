package com.io.kryptoblocks.trailscoialcampaign;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private ShareDialog shareDialog;
    private TextView page;
    private String TAG = "ShareActivity";
    Button btnShare,btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        btnShare = (Button) findViewById(R.id.btn_share);
        page = (TextView)findViewById(R.id.page_info);
        btnlogout = (Button) findViewById(R.id.logout);

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/1277771058959023",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */

                        page.setText("User page:  " +
                                response.getRawResponse() + "\n");
                    }
                }
        ).executeAsync();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_share:
                share();
                break;

           /* case R.id.getPosts:
                getPosts();
                break;*/

            case R.id.logout:
                logout();
                break;
        }
    }
    private void share(){
        shareDialog = new ShareDialog(this);
        List<String> taggedUserIds= new ArrayList<String>();
        taggedUserIds.add("{USER_ID}");
        taggedUserIds.add("{USER_ID}");
        taggedUserIds.add("{USER_ID}");

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/Kryptoblocks-898542326953252/"))
                .setContentTitle("Like ANd Share it")
                .setContentDescription("Pradeep Arya")
                .setShareHashtag(new ShareHashtag.Builder().setHashtag("#kryptoblocks").build())
                .setPeopleIds(taggedUserIds)
                .setPlaceId("{PLACE_ID}")
                .build();

        shareDialog.show(content);
    }
   /* private void getPosts(){
        new GraphRequest(
                AccessToken.getCurrentAccessToken(), "/me/posts", null, HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.e(TAG,response.toString());
                    }
                }
        ).executeAsync();
    }*/

    private void logout(){
        LoginManager.getInstance().logOut();
        Intent login = new Intent(ShareActivity.this, ShareActivity.class);
        startActivity(login);
        finish();
    }
}
