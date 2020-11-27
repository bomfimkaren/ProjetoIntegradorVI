package com.example.mainactivity.Consultar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainactivity.Banco.ControlerDB;
import com.example.mainactivity.Editar.EditarContas;
import com.example.mainactivity.Objetos.Lancamento;
import com.example.mainactivity.R;

import java.util.ArrayList;
import java.util.List;

public class LinhaSaldo extends BaseAdapter {


    private static LayoutInflater layoutInflater = null;


    List<Lancamento> lancamentos = new ArrayList<Lancamento>();


    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    ControlerDB banco_controle;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultarSaldo consultar;
    /*CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE CONTAS
    QUE VAI RETORNAR, DA NOSSA BASE DE DADOS
    */
//*************************************************************************************************
    public  LinhaSaldo (ConsultarSaldo consultar, List<Lancamento> lancamentos){
        this.lancamentos = lancamentos;
        this.consultar = consultar;
        this.layoutInflater = (LayoutInflater) this.consultar.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.banco_controle = new ControlerDB(consultar);
    }
    //*************************************************************************************************
    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount() {
        return lancamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //*************************************************************************************************
    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT linha_consultar.xml
        final View viewLinhas = layoutInflater.inflate(R.layout.linha, null);


        TextView codigo = (TextView) viewLinhas.findViewById(R.id.Codigo);

        TextView nome = (TextView) viewLinhas.findViewById(R.id.nome);

        TextView valor = (TextView) viewLinhas.findViewById(R.id.valor);

        TextView mes = (TextView) viewLinhas.findViewById(R.id.mes);

        TextView registro = (TextView) viewLinhas.findViewById(R.id.RegistroAtivo);

        Button buttonExcluir = (Button) viewLinhas.findViewById(R.id.buttonExcluir);

        Button buttonEditar = (Button) viewLinhas.findViewById(R.id.buttonEditar);



        mes.setText(this.mGetMes(lancamentos.get(position).getVencimento()));
        codigo.setText(String.valueOf(lancamentos.get(position).getId()));
        nome.setText(lancamentos.get(position).getTipo());
        valor.setText(lancamentos.get(position).getValor());
        //SETANDO OS CAMPO DA NOSSA VIEW SAldo

        if (lancamentos.get(position).getRegistro() == 1) {
            registro.setText("CONTA ATIVA:Sim");
        } else {
            registro.setText("CONTA ATIVA:Não");
        }

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                banco_controle.mExcluirSaldo(lancamentos.get(position).getId());

                Toast.makeText(consultar, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();
                AtualizarLista();
            }
        });

//CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRedirecionar = new Intent(consultar, EditarContas.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id_pessoa", lancamentos.get(position).getId());

                consultar.startActivity(intentRedirecionar);

                consultar.finish();
            }
        });
        return viewLinhas;
    }
    //*************************************************************************************************
//MÉTODO QUE RETORNA A DESCRIÇÃO DO MES POR CÓDIGO
    public String mGetMes(String codigoMes){

        if (codigoMes.equals("1")) {
            return "JANEIRO";
        } else if (codigoMes.equals("2")) {
            return "FEVEREIRO";
        } else if (codigoMes.equals("3")) {
            return "MARÇO";
        } else if (codigoMes.equals("4")) {
            return "ABRIL";
        } else if (codigoMes.equals("5")) {
            return "MAIO";
        } else if (codigoMes.equals("6")) {
            return "JUNHO";
        } else if (codigoMes.equals("7")) {
            return "JULHO";
        } else if (codigoMes.equals("8")) {
            return "AGOSTO";
        } else if (codigoMes.equals("9")) {
            return "SETEMBRO";
        } else if (codigoMes.equals("10")) {
            return "OUTUBRO";
        } else if (codigoMes.equals("11")) {
            return "NOVEMBRO";
        } else {
            return "DEZEMBRO";
        }

    }
    //************************************************************************************************
//ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){

        this.lancamentos.clear();
        this.lancamentos = banco_controle.mListarSaldo();
        this.notifyDataSetChanged();
    }

}
