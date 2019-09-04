package matc89.exercicio1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView labelMensagem;
    private Button btnCumprimentar;
    private EditText editNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCumprimentar = (Button)findViewById(R.id.btnCumprimentar);
        labelMensagem = (TextView)findViewById(R.id.labelMensagem);
        editNome = (EditText)findViewById(R.id.editNome);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String texto = prefs.getString("texto", "valor padrao");
        editNome.setText(texto);
    }
    public void clicou(View v) {
        String textoDigitado = "Alô," + editNome.getText().toString();
        labelMensagem.setText(textoDigitado);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("meutexto", editNome.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String meutexto = savedInstanceState.getString("meutexto");
        labelMensagem.setText(meutexto);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("texto", editNome.getText().toString());
        editor.commit();
    }
}

