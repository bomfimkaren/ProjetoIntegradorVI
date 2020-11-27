package com.example.mainactivity.Web;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void,Void, String> {

    public HttpService(){}

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("https://api.hgbrasil.com/finance/taxes?key=f7f5e4a6");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                resposta.append(scanner.next());
            }
            return resposta.toString();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
