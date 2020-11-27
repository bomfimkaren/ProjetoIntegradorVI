package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.Banco.DatabaseHelper;
import com.example.mainactivity.Objetos.Usuario;

public class Login extends AppCompatActivity {

    Button btEntrar, btCad;
    EditText edtLogin, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btEntrar = findViewById(R.id.btEntrar);
        btCad = findViewById(R.id.btCad);

        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);

        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        btCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    dbHelper.addUser(new Usuario(edtLogin.getText().toString(), edtSenha.getText().toString()));
                    Toast.makeText(Login.this, "Usuário add", Toast.LENGTH_SHORT).show();
                    edtLogin.setText("");
                    edtSenha.setText("");
                }else{
                    Toast.makeText(Login.this, "Campos vazios", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    Usuario user = dbHelper.queryUser(edtLogin.getText().toString(), edtSenha.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("user", user.getLogin());
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Bem Vindo " + user.getLogin(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Usuario não existe", Toast.LENGTH_SHORT).show();
                        edtSenha.setText("");
                    }
                }else{
                    Toast.makeText(Login.this, "Campos vazios", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtLogin.getText().toString()) || TextUtils.isEmpty(edtSenha.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}


