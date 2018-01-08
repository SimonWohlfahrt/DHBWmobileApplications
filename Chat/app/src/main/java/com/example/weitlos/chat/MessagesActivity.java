package com.example.weitlos.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MessagesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Intent i = getIntent();
        //ArrayList<ChatMessage> messages = i.getExtras().getParcelable("messages");
        ArrayList<ChatMessage> messages = (ArrayList<ChatMessage>)i.getExtras().get("msgs");

        for (ChatMessage msg:messages) {
            TextView t = new TextView(this);
            t.setText(msg.getUser() + ": " + msg.getMessage());
            ((LinearLayout)findViewById(R.id.layout_message)).addView(t);
        }


    }

    private ConstraintLayout createNewMessageRepresentation(ChatMessage msg) {
        ConstraintLayout msgRep = new ConstraintLayout(this);

        return msgRep;
        //((LinearLayout)findViewById(R.id.layout_message)).addView(msgRep);
    }
}
