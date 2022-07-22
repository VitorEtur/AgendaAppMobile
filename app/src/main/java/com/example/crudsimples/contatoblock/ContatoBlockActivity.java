package com.example.crudsimples.contatoblock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crudsimples.R;
import com.example.crudsimples.contatoimportante.ContatoImportante;
import com.example.crudsimples.contatoimportante.ListarContatosImportantesActivity;
import com.example.crudsimples.contatonormal.ListarContatosActivity;

import java.util.ArrayList;

public class ContatoBlockActivity extends AppCompatActivity {
/*Declarando atributos*/
    private EditText nomeblock;
    private EditText telefoneblock;
    private EditText cidadeblock;
    private ContatoBlockDAO bkdao;
    private ContatoBlock contatoBlock = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_block);
/*Vinculandos os campos do formulário aos atributos*/
        nomeblock = findViewById(R.id.editNomeBlock);
        telefoneblock = findViewById(R.id.editTelefoneBlock);
        cidadeblock = findViewById(R.id.editCidadeBlock);
        bkdao = new ContatoBlockDAO(this);
/*Metodo intent para chamar outra activity*/
        Intent it = getIntent();
        if (it.hasExtra("contatoblock")){
            contatoBlock = (ContatoBlock) it.getSerializableExtra("contatoblock");
            nomeblock.setText(contatoBlock.getNomeblock());
            telefoneblock.setText(contatoBlock.getTelefoneblock());
            cidadeblock.setText(contatoBlock.getCidadeblock());

        }
    }
/*Metodo para salvar o contato*/
    public void salvar(View view) {

        if (contatoBlock == null) {
            contatoBlock = new ContatoBlock();
            contatoBlock.setNomeblock(nomeblock.getText().toString());
            contatoBlock.setTelefoneblock(telefoneblock.getText().toString());
            contatoBlock.setCidadeblock(cidadeblock.getText().toString());
            long id = bkdao.inserir(contatoBlock);
            Toast.makeText(this, "Contato bloqueado com id: " + id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ListarContatosBloqueadosActivity.class);
            startActivity(intent);
            finish();
        }else {
            contatoBlock.setNomeblock(nomeblock.getText().toString());
            contatoBlock.setTelefoneblock(telefoneblock.getText().toString());
            contatoBlock.setCidadeblock(cidadeblock.getText().toString());
            bkdao.atualizarblock(contatoBlock);
            //*********** Exibe uma mensagem informando o Id do contato ao clicar nele
            Toast.makeText(this, "Contato bloqueado foi atualizado", Toast.LENGTH_SHORT).show();
            //*********** Chamar outra tela ao clicar no botão Salvar
            Intent intent = new Intent(getApplicationContext(), ListarContatosBloqueadosActivity.class);
            startActivity(intent); /*Inicia a activity*/
            finish(); /*finaliza a activity anterior*/
        }
    }
}