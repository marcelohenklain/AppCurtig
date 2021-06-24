package com.example.appcurtig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcurtig.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {
    //RECURSOS DO FIREBASE NECESSÁRIOS PARA AUTENTICAÇÃO
    private FirebaseAuth autenticacao;
    FirebaseDatabase banco = FirebaseDatabase.getInstance();
    DatabaseReference usuarios;

    //RECURSOS NECESSÁRIOS PARA OBTENÇÃO DOS DADOS DO USUÁRIO
    private TextInputLayout textInputLayoutNome;
    private TextInputLayout textInputLayoutIdade;
    private TextInputLayout textInputLayoutCurso;
    private TextInputLayout textInputLayoutUniversidade;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputSenha;

    //RECURSO NECESSÁRIO PARA REGISTRAR O CADASTRO DO USUÁRIO
    private Button buttonGravarDados;

    //IR PARA TELA DE CURTIGRAMA
    private Intent intentIrTelaCurtigrama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //OBTENDO A REFERÊNCIA DO BANCO
        usuarios = banco.getReference( "usuarios");

        //INICIALIZAÇÃO DOS COMPONENTES
        textInputLayoutNome = findViewById(R.id.textInputLayoutNome);
        textInputLayoutIdade = findViewById(R.id.textInpuLayoutIdade);
        textInputLayoutCurso = findViewById(R.id.textInputLayoutCurso);
        textInputLayoutUniversidade = findViewById(R.id.textInputLayoutUniversidade);
        textInputEmail = findViewById(R.id.textInputLayoutEmail);
        textInputSenha = findViewById(R.id.textInputLayoutSenha);
        buttonGravarDados = findViewById(R.id.buttonGravarDados);
        intentIrTelaCurtigrama = new Intent(Cadastro.this, Curtigrama.class);

        //BOTÃO CADASTRAR DADOS DO USUÁRIO
        buttonGravarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(textInputLayoutNome.getEditText().getText().toString());
                novoUsuario.setIdade(textInputLayoutIdade.getEditText().getText().toString());
                novoUsuario.setCurso(textInputLayoutCurso.getEditText().getText().toString());
                novoUsuario.setUniversidade(textInputLayoutUniversidade.getEditText().getText().toString());
                novoUsuario.setEmail(textInputEmail.getEditText().getText().toString());
                novoUsuario.setSenha(textInputSenha.getEditText().getText().toString());

                //Pegar a instância do FirebaseAuth
                autenticacao = FirebaseAuth.getInstance();

                //CRIAÇÃO DO USUÁRIO
                autenticacao.createUserWithEmailAndPassword(novoUsuario.getEmail(), novoUsuario.getSenha()).
                        addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    usuarios.push().setValue(novoUsuario);
                                    Toast.makeText(Cadastro.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                    //NESTE CASO PODEMOS IR PARA A TELA DE CURTIGRAMA
                                    startActivity(intentIrTelaCurtigrama);
                                }else{
                                    Toast.makeText(Cadastro.this, "Falha ao cadastar usuário", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}