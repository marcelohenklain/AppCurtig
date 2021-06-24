package com.example.appcurtig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //RECURSOS DO FIREBASE NECESSÁRIOS PARA AUTENTICAÇÃO
    private FirebaseAuth autenticacao;
    FirebaseDatabase banco = FirebaseDatabase.getInstance();

    //RECURSOS NECESSÁRIOS PARA QUE O SISTEMA DE LOGIN FUNCIONE
    private TextInputLayout textInputLayoutUsuario; //inicialização de componente
    private TextInputLayout textInputLayoutSenha; //inicialização de componente
    private Button buttonAcessar; //inicialização de componente
    private Intent intentIrTelaCurtigrama;

    //RECURSOS NECESSÁRIOS PARA QUE O SISTEMA DE CADASTRO FUNCIONE
    private Button buttonCadastrese; //inicialização de componente
    private Intent intentIrTelaCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //IR TELA CADASTRO
        buttonCadastrese = findViewById(R.id.buttonCadastrese);
        intentIrTelaCadastro = new Intent(MainActivity.this, Cadastro.class);

        //IR TELA CURTIGRAMA
        intentIrTelaCurtigrama = new Intent(MainActivity.this, Curtigrama.class);

        buttonCadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentIrTelaCadastro);
            }
        });

        //AUTENTICAÇÃO
        autenticacao = FirebaseAuth.getInstance();

        //Autenticação - condições para viabilizar o login
        textInputLayoutUsuario = findViewById(R.id.textInputLayoutUsuario);
        textInputLayoutSenha = findViewById(R.id.textInputLayoutSenha);
        buttonAcessar = findViewById(R.id.buttonAcessar);

        //IR TELA CURTIGRAMA EM CASO DE AUTENTICAÇÃO BEM SUCEDIDA
        buttonAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputLayoutUsuario.getEditText().getText().toString();
                String senha = textInputLayoutSenha.getEditText().getText().toString();

                logar(email, senha);
            }
        });
    }

    public void logar(String email, String senha){

        autenticacao.signInWithEmailAndPassword(email, senha).
                addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Usuário logado
                            textInputLayoutSenha.setError("");
                            Toast.makeText(MainActivity.this, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
                            startActivity(intentIrTelaCurtigrama);
                        }else{
                            textInputLayoutSenha.setError("Usuário e/ou senha inválidos");
                        }
                    }
                });
    }
}