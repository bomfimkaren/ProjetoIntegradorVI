package com.example.mainactivity.Web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.R;

public class WebServices extends AppCompatActivity implements View.OnClickListener {


    Button btnConsultar;
    EditText edtResposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_services);

        edtResposta = findViewById(R.id.edtResposta);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnConsultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try{
            String resposta = new HttpService().execute().get();
            edtResposta.setText(resposta.toString());

        }catch (Exception e){
            Toast.makeText(this, "Erro ao realizar consulta",
                    Toast.LENGTH_SHORT).show();

        }
    }
}