package com.example.mainactivity.Cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mainactivity.Banco.ControlerDB;
import com.example.mainactivity.MainActivity;
import com.example.mainactivity.Objetos.Lancamento;
import com.example.mainactivity.R;
import com.example.mainactivity.Uteis.ObjMes;

import java.util.ArrayList;
import java.util.List;

public class CadastroSaldo extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText mValor, mConta;
    Spinner mSpinnerMes;
    CheckBox mCheckBoxConta;
    Button mbtnVoltar, mbtnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_saldo);

        //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
        this.mCriarComponentes();

        //CARREGA AS OPÇÕES DO MES
        this.mCarregarMes();

        //CRIA OS EVENTOS DOS COMPONENTES
        this.mCriarEventos();
    }

    //************************************************************************************************
//VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void mCriarComponentes() {
        mConta = (EditText) this.findViewById(R.id.editNome);
        mValor = (EditText) this.findViewById(R.id.editValor);
        mSpinnerMes = (Spinner) this.findViewById(R.id.SpinnerMes);
        mCheckBoxConta = (CheckBox) this.findViewById(R.id.checkboxAtivo);
        mbtnSalvar = (Button) this.findViewById(R.id.btnSalvar);
        mbtnVoltar = (Button) this.findViewById(R.id.btnVoltar);
    }

    //************************************************************************************************
//CARREGA AS OPÇÕES DE MES PARA O COMPONENTE SPINNER
    protected void mCarregarMes() {
        ArrayAdapter<ObjMes> arrayAdapter;
        List<ObjMes> itens = new ArrayList<ObjMes>();

        itens.add(new ObjMes("1", "JANEIRO"));
        itens.add(new ObjMes("2", "FEVEREIRO"));
        itens.add(new ObjMes("3", "MARÇO"));
        itens.add(new ObjMes("4", "ABRIL"));
        itens.add(new ObjMes("5", "MAIO"));
        itens.add(new ObjMes("6", "JUNHO"));
        itens.add(new ObjMes("7", "JULHO"));
        itens.add(new ObjMes("8", "AGOSTO"));
        itens.add(new ObjMes("9", "SETEMBRO"));
        itens.add(new ObjMes("10", "OUTUBRO"));
        itens.add(new ObjMes("11", "NOVEMBRO"));
        itens.add(new ObjMes("12", "DEZEMBRO"));

        arrayAdapter = new ArrayAdapter<ObjMes>(this, android.R.layout.simple_spinner_item, itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerMes.setAdapter(arrayAdapter);
    }

    //*************************************************************************************************
    protected void mCriarEventos() {
        //CRIANDO EVENTO NO BOTÃO SALVAR
        mbtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvar_onClick();
            }
        });
//*************************************************************************************************
        //CRIANDO EVENTO NO BOTÃO VOLTAR
        mbtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Inicio = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Inicio);
                finish();
            }
        });
    }

    //*************************************************************************************************
    //VALIDA OS CAMPOS E SALVA AS INFORMAÇÕES NO BANCO DE DADOS
    protected void Salvar_onClick() {
        if (mConta.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "CAMPO OBRIGATORIO!", Toast.LENGTH_LONG).show();
            mConta.requestFocus();
        } else if (mValor.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "CAMPO OBRIGATORIO!", Toast.LENGTH_LONG).show();
        } else {
            /*CRIANDO UM OBJETO CONTA*/
            Lancamento lancamento = new Lancamento();

            /*SETANDO OS CAMPO CONTA*/
            lancamento.setTipo(mConta.getText().toString().trim());
            lancamento.setValor(mValor.getText().toString().trim());

            /*REALIZANDO UM CAST PARA PEGAR O OBJETO DO MES SELECIONADO*/
            ObjMes mes = (ObjMes) mSpinnerMes.getSelectedItem();
            /*SETANDO codigo MES*/
            lancamento.setVencimento(mes.getCodigo());

            /*SETA O REGISTRO COMO INATIVO*/
            lancamento.setRegistro((byte) 0);
            /*SE TIVER SELECIONADO SETA COMO ATIVO*/
            if (mCheckBoxConta.isChecked())
                lancamento.setRegistro((byte) 1);

            /*SALVANDO UM NOVO REGISTRO*/
            //new BancoDados(this).addConta(conta);
            new ControlerDB(this).mAddSaldo(lancamento);
            Toast.makeText(getApplicationContext(), "CONTA ADICIONADA COM SUCESSO!", Toast.LENGTH_LONG).show();
            LimparCampos();
        }
    }

    //**************************************************************************************************
    //LIMPA OS CAMPOS APÓS SALVAR AS INFORMAÇÕES
    protected void LimparCampos() {

        mConta.setText(null);
        mValor.setText(null);
        mCheckBoxConta.setChecked(false);
    }
}