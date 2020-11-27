package com.example.mainactivity.Receita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mainactivity.Banco.ControlerDB;
import com.example.mainactivity.MainActivity;
import com.example.mainactivity.Objetos.Conta;
import com.example.mainactivity.Objetos.Lancamento;
import com.example.mainactivity.R;

import java.util.ArrayList;
import java.util.List;

public class ReceitaMensal extends AppCompatActivity {
    Button mVoltar;
    TextView mtxtSaldo, mtxtDespesa, mtxtTotal, mtxtMsg;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    ControlerDB db = new ControlerDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receita_mensal);


        mtxtSaldo = (TextView) findViewById(R.id.txtSaldo);
        mtxtDespesa = (TextView) findViewById(R.id.txtDespesa);
        mtxtTotal = (TextView) findViewById(R.id.txtTotal);
        mtxtMsg = (TextView) findViewById(R.id.txtMensagem);

        mVoltar = (Button) findViewById(R.id.btnVoltar);


        mSubReceita();
        mEvento();

    }

    public void mEvento() {
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
    }

    //METODO QUE RECEBE OS VALORE E MOSTRA EM TELA
    public void mSubReceita() {
        //CRIA VARIAVEIS TIPO dOUBLE
        double saldo, despesa, total;
        saldo = mSomaSaldo();//RECEBE O METODO CALCULAR SALDO
        despesa = mSomaConta();//RECEBE O METODO CALCULA AS CONTAS
        total = saldo - despesa;//SUBTRAI OS DOIS METODOS

        //SETA AS CONTAS E SALDOS NOS TEXT VIU
        mtxtDespesa.setText(String.valueOf(("SOMA DAS CONTAS R$ " + mSomaConta())));
        mtxtSaldo.setText(String.valueOf(("VALOR DO SALDO  R$ " + mSomaSaldo())));

        //MOSTRA O RESULTADO DA SUBTRAÇÃO DOS METODOS
        mtxtTotal.setText(String.valueOf(String.format("CONTAS - SALDO = R$ " + total, 2)));

        //CONDIÇÃO PARA RECEITA
        if (total == 0 && total < 0) {
            mtxtMsg.setText(String.valueOf(" SUA RECEITA DO MES FECHO NEGATIVA EM: " + total));
        } else {
            mtxtMsg.setText(String.valueOf(" SUA RECEITA DO MES FECHO POSITIVA EM: " + total));
        }
    }

    //METODO QUE SOMA AS CONTAS CADASTRADAS
    public int mSomaConta() {
        int soma = 0;
        ArrayList List = new ArrayList();
        java.util.List<Conta> lista = db.mListarTodas();
        for (int i = 0; i < lista.size(); i++) {
            soma += Integer.parseInt(String.format(lista.get(i).getValor(), 2));//recupera o valor das contas e soma
        }
        return soma;
    }

    //METODO QUE CALCULA OS SALDOS CADASTRADOS
    public int mSomaSaldo() {
        int soma = 0;
        ArrayList List = new ArrayList();
        List<Lancamento> lista = db.mListarSaldo();
        for (int i = 0; i < lista.size(); i++) {
            soma += Integer.parseInt(String.format(lista.get(i).getValor(), 2));//recupera o valor das contas e soma
        }
        return soma;
    }
}
