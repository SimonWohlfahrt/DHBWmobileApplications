package com.example.weitlos.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {

    public final String username = "weitlos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMy();
    }

    public void onButtonShowClick(View sender) {
        String user = ((EditText)findViewById(R.id.editText_user)).getText().toString();

        if (user.length() == 0)
            getAll();
        else
            getByUser(user);
    }

    public void onButtonSendClick(View sender) {
        String text = ((EditText)findViewById(R.id.editText_message)).getText().toString();
        if (text.length() > 0)
        post(text);
    }

    protected void post(String text) {
        /*
        zum Senden der Nachricht an den Server im Namen eines Users
        http://webtechlecture.appspot.com/chat/posting/new?text=dertext&userid=username
         */
        ChatConnector conn = new ChatConnector(new ChatConnector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
                getMy();
            }
        });

        //String urltask = "http://webtechlecture.appspot.com/chat/posting/new?text=" + text + "&userid= " + username + "";
        String urltask = "http://webtechlecture.appspot.com/chat/posting/new?text=" + text + "&userid=" + username;
        conn.execute(urltask);
    }

    protected void getByUser(String user) {
        /*
        zum Auflisten der Nachrichten eines bestimmten Users :
        http://webtechlecture.appspot.com/chat/posting/list?userid=student
         */
        ChatConnector conn = new ChatConnector(new ChatConnector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
                //getByUser(username);
                showMessages(ConvertMessageJSONArrayToChatMessageList(output));
            }
        });

        String urltask = "http://webtechlecture.appspot.com/chat/posting/list?userid=" + user + "";
        conn.execute(urltask);
    }

    protected void getMy() {
        /*
        zum Auflisten der Nachrichten eines bestimmten Users :
        http://webtechlecture.appspot.com/chat/posting/list?userid=student
         */
        ChatConnector conn = new ChatConnector(new ChatConnector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
                //getByUser(username);
                loadMessagesToLayout(ConvertMessageJSONArrayToChatMessageList(output));
            }
        });

        //String urltask = "http://webtechlecture.appspot.com/chat/posting/list?userid=" + username + "";
        String urltask = "http://webtechlecture.appspot.com/chat/posting/list?userid=" + username;
        conn.execute(urltask);
    }

    private ArrayList<ChatMessage> ConvertMessageJSONArrayToChatMessageList(JSONArray a) {
        ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();

        for (int i = 0; i < a.length(); i++)
            try {
                messages.add(i, new ChatMessage(a.getJSONObject(i).get("userid").toString(), a.getJSONObject(i).get("text").toString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return messages;
    }

    private void loadMessagesToLayout(ArrayList<ChatMessage> messages) {
        LinearLayout l = findViewById(R.id.linearLayout_userMessages);
        l.removeAllViewsInLayout();
        for (int i = 0; i < messages.size(); i++) {
            TextView t = new TextView(this);
            t.setText(messages.get(i).getMessage());
            l.addView(t);
        }
    }

    protected void getAll() {
        /*
        zum Auflisten aller vorhandener Nachrichten:
        http://webtechlecture.appspot.com/chat/posting/list
         */
        ChatConnector conn = new ChatConnector(new ChatConnector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
                ArrayList<ChatMessage> messages = ConvertMessageJSONArrayToChatMessageList(output);
                showMessages(messages);
            }
        });
        String urltask = "http://webtechlecture.appspot.com/chat/posting/list";
        conn.execute(urltask);
    }

    public void showMessages(ArrayList<ChatMessage> messages) {
        Intent i = new Intent(this, MessagesActivity.class);
        //i.putExtra("messages", messages.get(0));
        //i.putParcelableArrayListExtra("dsf", messages);
        i.putExtra("msgs", messages);
        startActivity(i);
    }

}
