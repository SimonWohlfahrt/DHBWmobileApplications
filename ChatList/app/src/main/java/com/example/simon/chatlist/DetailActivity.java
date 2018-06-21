package com.example.simon.chatlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<ChatMessage> mChatMessages = new ArrayList<>();
    private ArrayAdapter<ChatMessage> mAdapter;
    private ListView listView;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        ((TextView) findViewById(R.id.textView)).setText(userid);

        listView = findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mChatMessages);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);

        fillList();
    }

    private void fillList() {
        getByUser();
    }

    private ArrayList<ChatMessage> getByUser() {
        ChatConnector chatConnector = new ChatConnector(new ChatConnector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray messages) {
                mChatMessages.clear();
                try {
                    for (int i = 0; i < messages.length(); i++) {
                        JSONObject message = messages.getJSONObject(i);
                        if (message.getString("userid").equals(userid))
                            mChatMessages.add(new ChatMessage(message.getString("text"), message.getString("userid") , Long.parseLong(message.getString("time"))));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        chatConnector.getRooms();

        return mChatMessages;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
