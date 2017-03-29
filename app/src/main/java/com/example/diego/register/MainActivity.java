package com.example.diego.register;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diego.register.DAO.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.diego.register.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button acessar = (Button)findViewById(R.id.button2);
        Button registrar = (Button)findViewById(R.id.button);
        final EditText email = (EditText) findViewById(R.id.editText);
        final EditText senha = (EditText) findViewById(R.id.senhalogin);
        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this, ListActivity.class));
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                Bundle mBundle = new Bundle();

                String message = "hey";


                DAO dao = new DAO( getApplicationContext() );

                //dao.select("*","USUARIO","EMAIL = '" + email +"' AND SENHA = '" + senha + "'");

                Cursor c = dao.select("*","USUARIO","EMAIL = '" + email.getText().toString() +"' AND SENHA = '" + senha.getText().toString() + "'");
                String email = "0", nomeUsuario = "";

                if (c.getCount() > 0) {
                    c.moveToFirst();
                    email = c.getString(2);
                    nomeUsuario = c.getString(1);
                    message = nomeUsuario;


                    Log.d("DEBUG", "PANICO: " + email);
                    Log.d("DEBUG", "HEY: " + nomeUsuario);
                    System.out.println(message);

                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);

                } else{
                    startActivity(new Intent(MainActivity.this, Register.class));

                }


                System.out.println(message);

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
