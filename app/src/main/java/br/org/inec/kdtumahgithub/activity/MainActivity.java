package br.org.inec.kdtumahgithub.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.org.inec.kdtumahgithub.R;

/**
 * Classe de implementação da tela inicial do aplicativo, nela foi implementado o splash inicial.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.main_activity_title);
        setContentView(R.layout.activity_main);

        //thread para chamar a segunda tela com um delay.
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(), UserSearchActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();

    }

}
