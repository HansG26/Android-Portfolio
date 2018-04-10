package com.example.android.geotrivia;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    // Aantal punten die speler behaalt
    private int totaalPunten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vindt wereldbol_imageview in activity_main.xml
        // Zet src attribute gelijk aan R.drawable.wereldbol (Picasso)
        ImageView imageView = findViewById(R.id.wereldbol_imageview);
        Picasso.get().load(R.drawable.wereldbol).into(imageView);
    }

    // Code wordt opgeroepen bij drukken op 'controleer button'
    public void controleer(View v) {
        // reset/zet totaalPunten gelijk aan 0
        totaalPunten = 0;

        // Vindt Views in activity_main.xml
        RadioButton provincieLuxemburgRadio = findViewById(R.id.provincie_luxemburg_radio);
        RadioButton turkijeRadio = findViewById(R.id.turkije_radio);
        RadioButton montVentouxRadio = findViewById(R.id.mont_ventoux_radio);
        RadioButton italiaansRadio = findViewById(R.id.italiaans_radio);
        RadioButton maasRadio = findViewById(R.id.maas_radio);
        RadioButton maanRadio = findViewById(R.id.maan_radio);

        TextView luxemburgVerbeterdTextView = findViewById(R.id.luxemburg_vebeterd);
        TextView turkijeVerbeterdTextView = findViewById(R.id.turkije_verbeterd);
        TextView montVentouxVebeterdTextView = findViewById(R.id.mont_ventoux_verbeterd);
        TextView italiaansVebeterdTextView = findViewById(R.id.italiaans_verbeterd);
        TextView maasVerbeterdTextView = findViewById(R.id.maas_verbeterd);
        TextView maanVerbeterdTextView = findViewById(R.id.maan_verbeterd);
        TextView hoofdstadVerbeterdTextView = findViewById(R.id.hoofdstad_verbeterd);
        TextView simpleMindsVerbeterdTextView = findViewById(R.id.simple_minds_verbeterd);

        EditText hoofdstadEditText = findViewById(R.id.hoofdstad_edittext);
        String hoofdstad = hoofdstadEditText.getText().toString();

        EditText simpleMindsEditText = findViewById(R.id.simple_minds_edittext);
        String simpleMinds = simpleMindsEditText.getText().toString();

        // Maakt verbeterd antwwoord zichtbaar
        luxemburgVerbeterdTextView.setVisibility(View.VISIBLE);
        turkijeVerbeterdTextView.setVisibility(View.VISIBLE);
        montVentouxVebeterdTextView.setVisibility(View.VISIBLE);
        italiaansVebeterdTextView.setVisibility(View.VISIBLE);
        maasVerbeterdTextView.setVisibility(View.VISIBLE);
        maanVerbeterdTextView.setVisibility(View.VISIBLE);
        hoofdstadVerbeterdTextView.setVisibility(View.VISIBLE);
        simpleMindsVerbeterdTextView.setVisibility(View.VISIBLE);

        // Controleer antwoorden
        if (provincieLuxemburgRadio.isChecked()) {
            totaalPunten++;
            luxemburgVerbeterdTextView.setTextColor(Color.GREEN);
        } else {
            luxemburgVerbeterdTextView.setTextColor(Color.RED);
        }

        if (turkijeRadio.isChecked()) {
            totaalPunten++;
            turkijeVerbeterdTextView.setTextColor(Color.GREEN);
        } else {
            turkijeVerbeterdTextView.setTextColor(Color.RED);
        }

        if (montVentouxRadio.isChecked()) {
            totaalPunten++;
            montVentouxVebeterdTextView.setTextColor(Color.GREEN);
        } else {
            montVentouxVebeterdTextView.setTextColor(Color.RED);
        }

        if (italiaansRadio.isChecked()) {
            totaalPunten++;
            italiaansVebeterdTextView.setTextColor(Color.GREEN);
        } else {
            italiaansVebeterdTextView.setTextColor(Color.RED);
        }

        if (maasRadio.isChecked()) {
            totaalPunten++;
            maasVerbeterdTextView.setTextColor(Color.GREEN);
        } else {
            maasVerbeterdTextView.setTextColor(Color.RED);
        }

        if (maanRadio.isChecked()) {
            totaalPunten++;
            maanVerbeterdTextView.setTextColor(Color.GREEN);
        } else {
            maanVerbeterdTextView.setTextColor(Color.RED);
        }

        if (hoofdstad.equals("Zwitserland") || hoofdstad.equals("zwitserland")) {
            totaalPunten += 2;
            hoofdstadVerbeterdTextView.setTextColor(Color.GREEN);
        } else {
            hoofdstadVerbeterdTextView.setTextColor(Color.RED);
        }

        if (simpleMinds.equals("Boston") || simpleMinds.equals("boston")) {
            totaalPunten += 2;
            simpleMindsVerbeterdTextView.setTextColor(Color.GREEN);
        } else {
            simpleMindsVerbeterdTextView.setTextColor(Color.RED);
        }

        // Laat Toast zien
        Toast toast = Toast.makeText(this, maakBericht(totaalPunten), Toast.LENGTH_SHORT);
        toast.show();

    }

    // Maakt String die in Toast komt
    private String maakBericht(int punten) {
        return "Je behaalde " + punten + " op 10";
    }
}
