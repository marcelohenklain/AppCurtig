package com.example.appcurtig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcurtig.model.Itenscurtig;
import com.example.appcurtig.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Curtigrama extends AppCompatActivity {

    //CRIANDO VÍNCULO COM O FIREBASE
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference item = database.getReference("itens/1");

    TextView textViewItemCurtigrama;
    RadioButton radioButtonSGSF;
    RadioButton radioButtonNGSF;
    RadioButton radioButtonSGNF;
    RadioButton radioButtonNGNF;
    Button buttonResponder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtigrama);

        textViewItemCurtigrama = findViewById(R.id.textViewItemCurtigrama);
        radioButtonSGSF = findViewById(R.id.radioButtonSGSF);
        radioButtonNGSF = findViewById(R.id.radioButtonNGSF);
        radioButtonSGNF = findViewById(R.id.radioButtonSGNF);
        radioButtonNGNF = findViewById(R.id.radioButtonNGNF);
        buttonResponder = findViewById(R.id.buttonResponder);

        item.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Itenscurtig item1 = snapshot.getValue(Itenscurtig.class);
                textViewItemCurtigrama.setText(item1.getContitem());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

/*
        //BOTÃO RESPONDER
        buttonResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario novoUsuario = new Usuario();
                //novoUsuario.setNome(textInputLayoutNome.getEditText().getText().toString());
                item.push().setValue(novoUsuario);
            }
        });
*/


    }
}