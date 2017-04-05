package com.example.diego.register;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diego.register.DAO.DAO;

import static android.R.id.message;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        String name = preferences.getString("Name", "");


        Log.d("DEBUG", "Nome shareed" + name);

        Button acessar = (Button)findViewById(R.id.button3);
        final EditText inputNome = (EditText) findViewById( R.id.editText4 );
        final EditText inputEmail = (EditText) findViewById( R.id.editText3 );
        final EditText inputSenha = (EditText) findViewById( R.id.editText5 );

        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues valores;
                long resultado;

                DAO dao = new DAO( getApplicationContext() );
                    editor.putString("Name",inputNome.getText().toString() );
                    editor.apply();
                    valores = new ContentValues();
                    valores.put("NOME", inputNome.getText().toString() );
                    valores.put("EMAIL", inputEmail.getText().toString() );
                    valores.put("SENHA", inputSenha.getText().toString() );

                resultado = dao.inserir("USUARIO",valores);


                startActivity(new Intent(Register.this, ListActivity.class));
            }
        });

    }

}
