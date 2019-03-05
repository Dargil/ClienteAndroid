package com.example.clienteandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clienteandroid.Modelo.Cliente;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    /**
     * Controles
     */
    private Button button;
    private EditText editText;
    private EditText editText2;
    private Context context = this;

    /**
     * Puerto
     */
    private static final int SERVERPORT = 5000;
    /**
     * HOST
     */
    private static final String ADDRESS = "192.168.137.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = ((Button) findViewById(R.id.button));
        editText = ((EditText) findViewById(R.id.editText));
        editText2 = ((EditText) findViewById(R.id.editText2));

        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (editText.getText().toString().length() > 0) {
                            MyATaskCliente myATaskYW = new MyATaskCliente();
                            myATaskYW.execute(editText.getText().toString());
                        } else {
                            Toast.makeText(context, "Escriba \"frase\" o \"libro\" ", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }//end:onCreate

    /**
     * Clase para interactuar con el servidor
     */
    class MyATaskCliente extends AsyncTask<String, Void, String> {
        /**
         * Ventana que bloqueara la pantalla del movil hasta recibir respuesta
         * del servidor
         */
        ProgressDialog progressDialog;

        /**
         * muestra una ventana emergente
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("Connecting to server");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        /**
         * Se conecta al servidor y trata resultado
         */
        @Override
        protected String doInBackground(String... values) {
            String received = "pailas";
            try {
                //Se conecta al servidor
                InetAddress serverAddr = InetAddress.getByName(ADDRESS);
                Log.i("I/TCP Client", "Connecting...");
                Socket socket = new Socket(serverAddr, SERVERPORT);
                Log.i("I/TCP Client", "Connected to server");

                //envia peticion de cliente
                Log.i("I/TCP Client", "Send data to server");
                Cliente myCliente = new Cliente(123, "pedrosdsd", 34, "@nomeimporta", "23424", "Users", "password");
                Token peticion = new Token("generico", "update", new String("insert into cliente values (1,'xcv',3,'cvcx','zcxvzx','zxcvcx','xzcvxc')"));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Gson enviar = new Gson();
                String jsonOutput = enviar.toJson(peticion);
                System.out.println("objeto en Json: " + jsonOutput);
                out.writeUTF(jsonOutput);

                //recibe respuesta del servidor y formatea a String
                Log.i("I/TCP Client", "Received data to server");
                Token respuesta = new Token();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                String jsonInput = entrada.readUTF();
                Gson gson = new Gson();
                //respuesta = gson.fromJson(jsonInput, respuesta.getClass());

                received =jsonInput.toString() ;
//cierra conexion
                socket.close();
                out.close();
                entrada.close();

            } catch (UnknownHostException ex) {
                Log.e("E/TCP Client", "" + ex.getMessage());
                return ex.getMessage();
            } catch (IOException ex) {
                Log.e("E/TCP Client", "" + ex.getMessage());
                return ex.getMessage();
            }
            return received;
        }

        /**
         * Oculta ventana emergente y muestra resultado en pantalla
         */
        @Override
        protected void onPostExecute(String value) {
            progressDialog.dismiss();
            editText2.setText(value);
        }
    }
}
