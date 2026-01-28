package com.example.a49busquedaasincrona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText editSearch;
    private Button btnSearch;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editSearch = findViewById(R.id.editSearch);
        btnSearch  = findViewById(R.id.btnSearch);
        webView    = findViewById(R.id.webView);

        // Configurar WebView
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(false);
        webView.setWebViewClient(new WebViewClient());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    new SearchTask().execute(query);
                }
            }
        });
    }

    // AsyncTask para realizar la búsqueda en segundo plano
    private class SearchTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String palabras = params[0];
            String pagina = "";
            String devuelve = "";

            try {
                URL url = new URL(
                        "https://duckduckgo.com/html/?q=" +
                                URLEncoder.encode(palabras, "UTF-8")
                );

                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestProperty(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
                );

                if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conexion.getInputStream())
                    );

                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        pagina += linea;
                    }
                    reader.close();

                    int indice = 0;
                    while ((indice = pagina.indexOf(
                            "<a rel=\"nofollow\" class=\"result__a\"", indice)) != -1) {

                        int inicioTitulo = pagina.indexOf(">", indice) + 1;
                        int finTitulo = pagina.indexOf("</a>", inicioTitulo);
                        if (finTitulo == -1) break;

                        String titulo = pagina.substring(inicioTitulo, finTitulo);
                        devuelve += "• " + titulo + "\n\n";
                        indice = finTitulo;
                    }
                }

                conexion.disconnect();

            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }

            return devuelve.isEmpty()
                    ? "No se encontraron resultados."
                    : devuelve;
        }

        @Override
        protected void onPostExecute(String resultado) {
            // Mostrar resultados como texto formateado
            String html = "<html><body style='font-size:16px;'>" +
                    "<pre>" + resultado + "</pre>" +
                    "</body></html>";

            webView.loadDataWithBaseURL(
                    null,
                    html,
                    "text/html",
                    "UTF-8",
                    null
            );
        }
    }
}
