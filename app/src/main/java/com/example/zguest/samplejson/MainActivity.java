package com.example.zguest.samplejson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get=(Button)findViewById(R.id.button_get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    postData();
                    Log.e("postdata","it crossed");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void postData() throws JSONException {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("https://devapi.zip.pr/v3/internal/ddn?searchText=HYD-CA05CV00&latitude=17.4617144&longitude=78.3428429");
        JSONObject json = new JSONObject();

        try {
            // JSON data:
            json.put("Content-Type", "application/json");
            json.put("x-zippr-api-key", "7077a87616779beedb57c6b231ebd906ce78c2178887c48e74905e6d327417f9");

            JSONArray postjson=new JSONArray();
            postjson.put(json);
            Log.e("to POSTDATA JSON","it crossed");
            // Post the data:
            httppost.setHeader("json",json.toString());
            httppost.getParams().setParameter("jsonpost",postjson);

            // Execute HTTP Post Request
            System.out.print(json);
            HttpResponse response = httpclient.execute(httppost);

            // for JSON:
            if(response != null)
            {
                InputStream is = response.getEntity().getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    Log.e("try_while","it crossed");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(this, subActivity.class);
                intent.putExtra("JSON",sb.toString());
                startActivity(intent);
                Log.e("to intent","it crossed");
            }

            //tv.setText(text);

        }catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }
}
