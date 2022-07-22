package com.example.crudsimples.contatoimportante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsimples.R;

public class ContatoImportanteActivity extends AppCompatActivity {

    private EditText nomeimportante;
    private EditText cidadeimportante;
    private EditText telefoneimportante;
    private ContatoImportanteDAO ctdao;
    private ContatoImportante contatoImportante = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_importante);

        nomeimportante = findViewById(R.id.editNomeBlock);
        cidadeimportante = findViewById(R.id.editCidadeBlock);
        telefoneimportante = findViewById(R.id.editTelefoneBlock);
        ctdao = new ContatoImportanteDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("contatoimportante")){
            contatoImportante = (ContatoImportante) it.getSerializableExtra("contatoimportante");
            nomeimportante.setText(contatoImportante.getNomeimportante());
            telefoneimportante.setText(contatoImportante.getTelefoneimportante());
            cidadeimportante.setText(contatoImportante.getCidadeimportante());

        }
    }

    public void salvar(View view){

        if (contatoImportante == null) {
            contatoImportante = new ContatoImportante();
            contatoImportante.setNomeimportante(nomeimportante.getText().toString());
            contatoImportante.setCidadeimportante(cidadeimportante.getText().toString());
            contatoImportante.setTelefoneimportante(telefoneimportante.getText().toString());
            long id = ctdao.inserir(contatoImportante);
            Toast.makeText(this, "Contato Importante inserido com id: " + id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ListarContatosImportantesActivity.class);
            startActivity(intent);
            finish();
        }else{
            contatoImportante.setNomeimportante(nomeimportante.getText().toString());
            contatoImportante.setCidadeimportante(cidadeimportante.getText().toString());
            contatoImportante.setTelefoneimportante(telefoneimportante.getText().toString());
            ctdao.atualizarimportante(contatoImportante);
            //*********** Exibe uma mensagem informando o Id do contato ao clicar nele
            Toast.makeText(this, "Contato foi atualizado", Toast.LENGTH_SHORT).show();
            //*********** Chamar outra tela ao clicar no botão Salvar
            Intent intent = new Intent(getApplicationContext(), ListarContatosImportantesActivity.class);
            startActivity(intent);
            finish();
        }
    }
}