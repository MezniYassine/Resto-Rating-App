package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.Intent;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        String nomUtilisateur = getIntent().getStringExtra("nom");
        String prenUtilisateur = getIntent().getStringExtra("prenom");
        TextView textViewMessage = findViewById(R.id.textViewMessage);
        textViewMessage.setText("Bonjour " + nomUtilisateur +" "+prenUtilisateur+ ", merci de compléter ce formulaire.");
        Button buttonValider = findViewById(R.id.buttonValiderRestaurant);
        dbHelper dbHelper=new dbHelper(SecondActivity.this);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // No need to 'findViewById' again if you already have your fields as member variables.
                // Initialize EditTexts and RatingBar only once in onCreate, as member variables
                TextView editTextNom=findViewById(R.id.editTextNomRestaurant);
                TextView editTextAdresse=findViewById(R.id.editTextAdresseRestaurant);
                TextView editTextAvis=findViewById(R.id.editTextAvisRestaurant);
                RatingBar ratingBar=findViewById(R.id.ratingBar);
                String nomRestaurant = editTextNom.getText().toString().trim();
                String adresseRestaurant = editTextAdresse.getText().toString().trim();
                String avisRestaurant = editTextAvis.getText().toString().trim();
                float ratingRestaurant = ratingBar.getRating();

                boolean isValid = true;

                if (nomRestaurant.isEmpty()) {
                    editTextNom.setError("Le nom du restaurant est requis.");
                    isValid = false;
                }

                if (adresseRestaurant.isEmpty()) {
                    editTextAdresse.setError("L'adresse du restaurant est requise.");
                    isValid = false;
                }

                if (avisRestaurant.isEmpty()) {
                    editTextAvis.setError("Un avis sur le restaurant est requis.");
                    isValid = false;
                }

                if (ratingRestaurant == 0) {
                    Toast.makeText(SecondActivity.this, "Veuillez donner une note au restaurant.", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                if (isValid) {
                    long result = dbHelper.addRestaurant(nomRestaurant, adresseRestaurant, avisRestaurant, ratingRestaurant);
                    if (result == -1) {
                        Toast.makeText(SecondActivity.this, "Echec", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SecondActivity.this, "Restaurant ajouté avec succés!", Toast.LENGTH_SHORT).show();
                        // Proceed with navigating to another Activity or refreshing the current one
                    }
                    Intent intent = new Intent(SecondActivity.this, DisplayRestaurantActivity.class);
                    // Use putExtra to pass data or consider using Parcelable to pass complex objects
                    intent.putExtra("nomRestaurant", nomRestaurant);
                    intent.putExtra("adresseRestaurant", adresseRestaurant);
                    intent.putExtra("avisRestaurant", avisRestaurant);
                    intent.putExtra("ratingRestaurant", ratingRestaurant);
                    startActivity(intent);
                }
            }
        });
    }
}