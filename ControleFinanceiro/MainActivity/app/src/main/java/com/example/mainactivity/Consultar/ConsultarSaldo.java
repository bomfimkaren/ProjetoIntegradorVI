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
import com.example.mainactivity.Objetos.Lancamento;
import com.example.mainactivity.R;
import com.example.mainactivity.Receita.ReceitaMensal;

import java.util.ArrayList;
import java.util.List;

 public class ConsultarSaldo extends AppCompatActivity {
    //INSTANCIANDO OBJ
    ListView mlistar;
    Button mVoltar, mReceita;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_saldo);
        mlistar = (ListView) findViewById(R.id.lista);
        mVoltar = (Button) findViewById(R.id.buttonVoltar);
        mReceita = (Button) findViewById(R.id.btnReceita);

        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.CarregarCOntaCadastradas();

        //CHAMA O MÉTODO QUE CRIA O EVENTO PARA O BOTÃO VOLTAR
        this.CriarEvento();
    }


    protected void CriarEvento() {

        //MÉTODO QUE CRIA EVENTO PARA O BOTÃO VOLTAR
        mVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentInicio = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentInicio);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
        //*************************************************************************************************
        //MÉTODO QUE LISTAR A ACTIVE RECEITA
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
    public void CarregarCOntaCadastradas() {
        ControlerDB db = new ControlerDB(this);

        //BUSCA AS CONTAS CADASTRADAS
        List<Lancamento> contaList = db.mListarSaldo();

        //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
        mlistar.setAdapter(new LinhaSaldo(this, contaList));
    }
}