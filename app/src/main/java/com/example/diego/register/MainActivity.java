package com.example.diego.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.diego.register.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button acessar = (Button)findViewById(R.id.button2);
        Button registrar = (Button)findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this, ListActivity.class));
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                Bundle mBundle = new Bundle();

                String message = editText.getText().toString();
                System.out.println(message);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

    }
}
