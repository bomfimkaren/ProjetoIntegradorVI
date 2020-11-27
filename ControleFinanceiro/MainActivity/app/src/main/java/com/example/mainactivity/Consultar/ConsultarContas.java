package com.example.mainactivity.Consultar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.mainactivity.Banco.ControlerDB;
import com.example.mainactivity.MainActivity;
import com.example.mainactivity.Objetos.Conta;
import com.example.mainactivity.R;
import com.example.mainactivity.Receita.ReceitaMensal;

import java.util.ArrayList;
import java.util.List;

public class ConsultarContas extends AppCompatActivity {
    ListView mlistar;
    Button mVoltar, mReceita;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    //ControlerDB db = new ControlerDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_contas);
        mlistar = (ListView) findViewById(R.id.lista);
        mVoltar = (Button)findViewById(R.id.buttonVoltar);
        mReceita = (Button)findViewById(R.id.btnReceita);

       // listarConta();
        //listarSaldo();
        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.CarregarCOntaCadastradas();

        //CHAMA O MÉTODO QUE CRIA O EVENTO PARA O BOTÃO VOLTAR
        this.CriarEvento();
    }


    protected  void CriarEvento(){

        mVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentInicio = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentInicio);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
        mReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentInicio = new Intent(getApplicationContext(), ReceitaMensal.class);
                startActivity(intentInicio);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
    }
    //*************************************************************************************************
    //MÉTODO QUE CONSULTA AS CONTA CADASTRADAS
    public void CarregarCOntaCadastradas(){
        ControlerDB db = new ControlerDB(this);

        //BUSCA AS CONTAS CADASTRADAS
        List<Conta> contaList = db.mListarTodas();

        //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
        mlistar.setAdapter(new Linha(this,contaList));
    }
}
