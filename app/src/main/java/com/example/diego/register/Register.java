package com.example.diego.register;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diego.register.DAO.DAO;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

                    valores = new ContentValues();
                    valores.put("NOME", inputNome.getText().toString() );
                    valores.put("EMAIL", inputEmail.getText().toString() );
                    valores.put("SENHA", inputSenha.getText().toString() );

                resultado = dao.inserir("USUARIO",valores);


                startActivity(new Intent(Register.this, ListActivity.class));
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
