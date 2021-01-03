package com.nubari.arithmeticgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSubmit(View view) {
        EditText usernameView = (EditText) findViewById(R.id.username);
        String username = usernameView.getText().toString();
        Intent intent = new Intent(this, QuestioniareActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}