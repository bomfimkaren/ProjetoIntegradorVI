package com.example.mainactivity.Objetos;

//================================================================================================
//CLASSE QUE CRIA O OBJETO SALDO.
public class Lancamento extends Conta {
    public Integer getS_id() {
        return S_id;
    }

    public void setS_id(Integer s_id) {
        S_id = s_id;
    }

    public String getS_vencimento() {
        return S_vencimento;
    }

    public void setS_vencimento(String s_vencimento) {
        S_vencimento = s_vencimento;
    }

    public String getS_valor() {
        return S_valor;
    }

    public void setS_valor(String s_valor) {
        S_valor = s_valor;
    }

    public byte getS_registro() {
        return S_registro;
    }

    public void setS_registro(byte s_registro) {
        S_registro = s_registro;
    }

    public String getS_tipo() {
        return S_tipo;
    }

    public void setS_tipo(String s_tipo) {
        S_tipo = s_tipo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    private Integer S_id;
    private String S_vencimento;
    private String S_valor;
    private byte S_registro;
    private String S_tipo;
    private String saldo;

}
