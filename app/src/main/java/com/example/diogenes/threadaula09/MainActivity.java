package com.example.diogenes.threadaula09;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://pbs.twimg.com/profile_images/988878807552716803/Waq0XZtz_400x400.jpg";
    private ProgressBar progress;
    private ImageView imgView;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.img);
        progress = (ProgressBar) findViewById(R.id.progress);
        // Faz o download
        downloadImagem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            downloadImagem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Faz o download da imagem em uma nova thread
    private void downloadImagem() {
        // Cria uma AsyncTask
        DownloadTask task = new DownloadTask();
        // Executa a task/thread
        task.execute("oi gente utilizando AsyncTask");
    }

    private class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Mostra o ProgressBar para fazer a animacao
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // Faz o download da imagem
            try {
                Log.v("livroandroid", params[0]);
                bitmap = downloadBitmap(URL);
            } catch (Exception e) {
                Log.e("livroandroid", e.getMessage(), e);
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                // Esconde o progress
                progress.setVisibility(View.INVISIBLE);
                // Atualiza a imagem
                imgView.setImageBitmap(bitmap);
            }
        }
    }

    public Context getContext() {
        return this;
    }

    public static Bitmap downloadBitmap(String url) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        // Faz o download da imagem
        Bitmap bitmap = null;
        InputStream in = new URL(url).openStream();
        // Converte a InputStream do Java para Bitmap
        bitmap = BitmapFactory.decodeStream(in);
        in.close();
        return bitmap;
    }
}
