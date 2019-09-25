
package com.example.preferenciascorusuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

public class PreferenciasCorActivity extends AppCompatActivity {


    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button botaoSalvar;
    private ConstraintLayout layout;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias_cor);

        radioGroup = findViewById(R.id.radioGroupId);
        botaoSalvar = findViewById(R.id.botaoId);
        layout = findViewById(R.id.layoutId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recupera o ID do radioButton selecionado ao ser clicado
                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();

                //para o id do radioButton ser recuperado sempre que ele for maior que ZERO
                if (idRadioButtonEscolhido > 0) {
                    //usar o metodo findViewById para passar o butao selecionado
                    radioButtonSelecionado = findViewById(idRadioButtonEscolhido);

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //chave escolhida + recuperar o que foi escolhido na tela

                    //criar string para colocar o radioButtonSelecionado
                    String corEscolhida = radioButtonSelecionado.getText().toString();
                    editor.putString("corEscolhida", corEscolhida);
                    //para dizer que realmente finalizamos a atv e pode gravar o arquivo
                    editor.commit();

                    setBackground(corEscolhida);

                }
            }
        });

        //recuperar a cor salva
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        if (sharedPreferences.contains("corEscolhida")) {
            String cor = sharedPreferences.getString("corEscolhida", "Branco");
            setBackground(cor);
        }

    }

    //para setar/tratar a cor do background escolhida
    private void setBackground(String cor) {

        if(cor.equals("Rosa")){

            layout.setBackgroundColor(Color.parseColor("#d81b60"));
        }else if(cor.equals("Azul")){

            layout.setBackgroundColor(Color.parseColor("#ff0099cc"));
        }else if(cor.equals("Verde")){

            layout.setBackgroundColor(Color.parseColor("#ff669900"));
        }
    }

}
