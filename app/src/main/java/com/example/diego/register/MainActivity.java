package com.example.diego.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
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
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        String value = preferences.getString("Name","");
//        Log.d("DEBUG", "DSAAAA  " + value);
        if ( !value.isEmpty() ){
            Log.e("DEBUG/SESSAO", "Sessao existente! (" + value + ")" );
//            intent.putExtra(EXTRA_MESSAGE);
            startActivity( new Intent(MainActivity.this, ListActivity.class) );

        } else {
            Log.e("DEBUG/SESSAO", "Sessao nao encontrada! (" + value + ")" );
        }

        setContentView(R.layout.activity_main);
        Button acessar = (Button)findViewById(R.id.button2);
        Button registrar = (Button)findViewById(R.id.button);
        final EditText email = (EditText) findViewById(R.id.editText);
        final EditText senha = (EditText) findViewById(R.id.senhalogin);
        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this, ListActivity.class));
                intent = new Intent(MainActivity.this, ListActivity.class);
                Bundle mBundle = new Bundle();

                String message = "hey";


                DAO dao = new DAO( getApplicationContext() );

                //dao.select("*","USUARIO","EMAIL = '" + email +"' AND SENHA = '" + senha + "'");

                Cursor c = dao.select("*","USUARIO","EMAIL = '" + email.getText().toString() +"' AND SENHA = '" + senha.getText().toString() + "'");
                String email = "0", nomeUsuario = "";
                Log.d("DEBUG", "HEYUYYYYYYYYY " + email.toString());
                if (c.getCount() > 0) {
                    c.moveToFirst();
                    email = c.getString(2);
                    nomeUsuario = c.getString(1);
                    message = nomeUsuario;
                    editor.putString("Name",message);
                    editor.apply();

//                    prefs.edit().putString(nomeUsuario).apply();
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
