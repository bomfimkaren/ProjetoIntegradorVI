package com.example.mainactivity.Cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mainactivity.Banco.Conexao;
import com.example.mainactivity.Banco.ControlerDB;
import com.example.mainactivity.MainActivity;
import com.example.mainactivity.Objetos.Conta;
import com.example.mainactivity.R;
import com.example.mainactivity.Uteis.ObjMes;

import java.util.ArrayList;
import java.util.List;

public class CadastroConta extends AppCompatActivity {
    /*COMPONENTES DA TELA*/
    EditText mValor,mFone,mEmail,mTipo;
    EditText mNome;
    Spinner mSpinnerMes;
    CheckBox mCheckBoxConta;
    Button mbtnVoltar, mbtnSalvar, mbtnAlterar;
    ControlerDB db = new ControlerDB(this);
    Conexao conexao = new Conexao(this);
    Context context;
    ArrayAdapter<ObjMes> arrayAdapter;
    // AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_conta);

        //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
        this.mCriarComponentes();

        //CARREGA AS OPÇÕES DO MES
        this.mCarregarMes();

        //CRIA OS EVENTOS DOS COMPONENTES
        this.mCriarEventos();

    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void mCriarComponentes() {
        mTipo = (EditText) this.findViewById(R.id.editNome);
        mEmail = (EditText) this.findViewById(R.id.mEmail);
        mNome = (EditText) this.findViewById(R.id.editUsu);
        mFone = (EditText) this.findViewById(R.id.edtFone);
        mValor = (EditText) this.findViewById(R.id.editValor);
        mSpinnerMes = (Spinner) this.findViewById(R.id.SpinnerMes);
        mCheckBoxConta = (CheckBox) this.findViewById(R.id.checkboxAtivo);
        mbtnSalvar = (Button) this.findViewById(R.id.btnSalvar);
        mbtnVoltar = (Button) this.findViewById(R.id.btnVoltar);
        mbtnAlterar = (Button) this.findViewById(R.id.btnAlterar);
    }

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

    protected void Salvar_onClick(){
        if(mNome.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"CAMPO OBRIGATORIO!", Toast.LENGTH_LONG).show();
            mNome.requestFocus();
        } else if (mValor.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"CAMPO OBRIGATORIO!", Toast.LENGTH_LONG).show();
        }else {

            Conta conta = new Conta();

            conta.setTipo(mTipo.getText().toString().trim());

            conta.setValor(mValor.getText().toString().trim());


            ObjMes mes = (ObjMes)mSpinnerMes.getSelectedItem();

            conta.setVencimento(mes.getCodigo());


            conta.setRegistro((byte)0);

            if(mCheckBoxConta.isChecked())
                conta.setRegistro((byte)1);


            new ControlerDB(this).mSalvar(conta);
            Toast.makeText(getApplicationContext(),"CONTA ADICIONADA COM SUCESSO!", Toast.LENGTH_LONG).show();
            LimparCampos();
        }
    }


    protected void LimparCampos(){

        mNome.setText(null);
        mFone.setText(null);

        mEmail.setText(null);
        mValor.setText(null);
        mCheckBoxConta.setChecked(false);
    }
}