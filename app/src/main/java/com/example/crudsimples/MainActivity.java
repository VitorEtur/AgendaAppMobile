package com.example.crudsimples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //*********** Declarando atributos
    private EditText nome;
    private EditText telefone;
    private EditText cidade;
    private ContatoDAO contatoDAO;
    private Contato contato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();


        setContentView(R.layout.activity_main);
//*********** Vinculandos os campos do formulário aos atributos
        nome = findViewById(R.id.editNome);
        telefone = findViewById(R.id.editTelefone);
        cidade = findViewById(R.id.editCidade);
        contatoDAO = new ContatoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("contato")){
            contato = (Contato) it.getSerializableExtra("contato");
            nome.setText(contato.getNome());
            telefone.setText(contato.getTelefone());
            cidade.setText(contato.getCidade());

        }

    }
    //*********** Método para salvar
    public void salvar (View view) {

        if (contato == null) {

            contato = new Contato();
            contato.setNome(nome.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setCidade(cidade.getText().toString());
            long id = contatoDAO.inserir(contato);
            Toast.makeText(this, "Contato inserido com id: " + id, Toast.LENGTH_SHORT).show();
            //*********** Chamar outra tela ao clicar no botão Salvar
            Intent intent = new Intent(getApplicationContext(), ListarContatosActivity.class);
            startActivity(intent);
        }else{
            contato.setNome(nome.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setCidade(cidade.getText().toString());
            contatoDAO.atualizar(contato);
            //*********** Exibe uma mensagem informando o Id do contato ao clicar nele
            Toast.makeText(this, "Contato foi atualizado", Toast.LENGTH_SHORT).show();
            //*********** Chamar outra tela ao clicar no botão Salvar
            Intent intent = new Intent(getApplicationContext(), ListarContatosActivity.class);
            startActivity(intent);
        }


    }

}