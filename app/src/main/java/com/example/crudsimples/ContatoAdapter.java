package com.example.crudsimples;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {

    private List<Contato> contatos;
    private Activity activity;

    public ContatoAdapter(Activity activity,List<Contato> contatos){
        this.activity = activity;
        this.contatos = contatos;
    }

    @Override
    public int getCount(){
        return contatos.size();
    }

    @Override
    public Object getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId (int i) {
        return contatos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewAdapter = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);

        TextView nome = viewAdapter.findViewById(R.id.txt_nome);
        TextView telefone = viewAdapter.findViewById(R.id.txt_telefone);
        TextView cidade = viewAdapter.findViewById(R.id.txt_cidade);
        Contato a = contatos.get(i);
        nome.setText (a.getNome());
        telefone.setText(a.getTelefone());
        cidade.setText(a.getCidade());

        return viewAdapter;
    }


}
