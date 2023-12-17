package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Button buttonValider = findViewById(R.id.buttonValider);

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNom = findViewById(R.id.editTextNom);
                EditText editTextPrenom = findViewById(R.id.editTextPrenom);
                EditText editTextAdresse = findViewById(R.id.editTextAdresse);
                EditText editTextTelephone = findViewById(R.id.editTextTelephone);
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                TextView alert=findViewById(R.id.alert);
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                String tel = editTextTelephone.getText().toString();
                String email = editTextEmail.getText().toString();
                boolean isValid = true;
                if (nom.isEmpty()) {
                    editTextNom.setError("Le champ 'Nom' est requis.");
                    isValid = false;
                }

                if (prenom.isEmpty()) {
                    editTextPrenom.setError("Le champ 'Prénom' est requis.");
                    isValid = false;
                }

                if (adresse.isEmpty()) {
                    editTextAdresse.setError("Le champ 'Adresse' est requis.");
                    isValid = false;
                }

                if (tel.isEmpty()) {
                    editTextTelephone.setError("Le champ 'Téléphone' est requis.");
                    isValid = false;
                } else if (tel.length() != 8) {
                    editTextTelephone.setError("Le numéro de téléphone doit contenir 8 chiffres.");
                    isValid = false;
                }
                if (email.isEmpty()) {
                    editTextEmail.setError("Le champ 'Email' est requis.");
                    isValid = false;
                } else if (!email.contains("@")) {
                    editTextEmail.setError("L'email doit contenir un '@'.");
                    isValid = false;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Adresse e-mail non valide.");
                    isValid = false;
                }if (isValid) {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                    // Passer à l'activité SecondActivity avec les données du restaurant
                    Intent intent = new Intent(Sign_up.this, SecondActivity.class);
                    intent.putExtra("nom", nom);
                    intent.putExtra("prenom", prenom);
                    intent.putExtra("adresse", adresse);
                    intent.putExtra("telephone", tel);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
                else{
                    alert.setText("Veuillez corriger les erreurs avant de soumettre.");
                }
            }
        });
    }
}
