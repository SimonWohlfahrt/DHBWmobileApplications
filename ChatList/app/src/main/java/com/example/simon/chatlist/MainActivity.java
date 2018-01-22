package com.example.simon.chatlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<ChatMessage> mChatMessages = new ArrayList<>();
    private ArrayAdapter<ChatMessage> mAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mChatMessages);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);

        fillList();
    }

    private void fillList() {
        getAll();
    }

    private ArrayList<ChatMessage> getAll() {
        ChatConnector chatConnector = new ChatConnector(new ChatConnector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray messages) {
                mChatMessages.clear();
                try {
                    for (int i = 0; i < messages.length(); i++) {
                        JSONObject message = messages.getJSONObject(i);
                        mChatMessages.add(new ChatMessage(message.getString("text"), message.getString("userid") , Long.parseLong(message.getString("time"))));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        chatConnector.execute();

        return mChatMessages;
    }

    private ArrayList<ChatMessage> getAllByUser(String username) {
        return mChatMessages;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        ChatMessage message = mAdapter.getItem(i);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("userid", message.getUser());
        startActivity(intent);
    }
}
