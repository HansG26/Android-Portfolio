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

    private int totaalPunten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.wereldbol_imageview);
        Picasso.get().load(R.drawable.wereldbol).into(imageView);
    }

    public void controleer(View v) {
        totaalPunten = 0;

        RadioButton provincieLuxemburgRadio = findViewById(R.id.provincie_luxemburg_radio);
        RadioButton turkijeRadio = findViewById(R.id.turkije_radio);
        RadioButton montVentouxRadio = findViewById(R.id.mont_ventoux_radio);
        RadioButton italiaansRadio = findViewById(R.id.italiaans_radio);
        RadioButton maasRadio = findViewById(R.id.maas_radio);
        RadioButton maanRadio = findViewById(R.id.maan_radio);

        TextView luxemburgJuistTextView = findViewById(R.id.luxemburg_juist);
        TextView turkijeJuistTextView = findViewById(R.id.turkije_juist);
        TextView montVentouxJuistTextView = findViewById(R.id.mont_ventoux_juist);
        TextView italiaansJuistTextView = findViewById(R.id.italiaans_juist);
        TextView maasJuistTextView = findViewById(R.id.maas_juist);
        TextView maanJuistTextView = findViewById(R.id.maan_juist);
        TextView hoofdstadJuistTextView = findViewById(R.id.hoofdstad_juist);
        TextView simpleMindsJuistTextView = findViewById(R.id.simple_minds_juist);

        EditText hoofdstadEditText = findViewById(R.id.hoofdstad_edittext);
        String hoofdstad = hoofdstadEditText.getText().toString();

        EditText simpleMindsEditText = findViewById(R.id.simple_minds_edittext);
        String simpleMinds = simpleMindsEditText.getText().toString();

        luxemburgJuistTextView.setVisibility(View.VISIBLE);
        turkijeJuistTextView.setVisibility(View.VISIBLE);
        montVentouxJuistTextView.setVisibility(View.VISIBLE);
        italiaansJuistTextView.setVisibility(View.VISIBLE);
        maasJuistTextView.setVisibility(View.VISIBLE);
        maanJuistTextView.setVisibility(View.VISIBLE);
        hoofdstadJuistTextView.setVisibility(View.VISIBLE);
        simpleMindsJuistTextView.setVisibility(View.VISIBLE);

        if (provincieLuxemburgRadio.isChecked()) {
            totaalPunten++;
            luxemburgJuistTextView.setTextColor(Color.GREEN);
        } else {
            luxemburgJuistTextView.setTextColor(Color.RED);
        }

        if (turkijeRadio.isChecked()) {
            totaalPunten++;
            turkijeJuistTextView.setTextColor(Color.GREEN);
        } else {
            turkijeJuistTextView.setTextColor(Color.RED);
        }

        if (montVentouxRadio.isChecked()) {
            totaalPunten++;
            montVentouxJuistTextView.setTextColor(Color.GREEN);
        } else {
            montVentouxJuistTextView.setTextColor(Color.RED);
        }

        if (italiaansRadio.isChecked()) {
            totaalPunten++;
            italiaansJuistTextView.setTextColor(Color.GREEN);
        } else {
            italiaansJuistTextView.setTextColor(Color.RED);
        }

        if (maasRadio.isChecked()) {
            totaalPunten++;
            maasJuistTextView.setTextColor(Color.GREEN);
        } else {
            maasJuistTextView.setTextColor(Color.RED);
        }

        if (maanRadio.isChecked()) {
            totaalPunten++;
            maanJuistTextView.setTextColor(Color.GREEN);
        } else {
            maanJuistTextView.setTextColor(Color.RED);
        }

        if (hoofdstad.equals("Zwitserland") || hoofdstad.equals("zwitserland")) {
            totaalPunten += 2;
            hoofdstadJuistTextView.setTextColor(Color.GREEN);
        } else {
            hoofdstadJuistTextView.setTextColor(Color.RED);
        }

        if (simpleMinds.equals("Boston") || simpleMinds.equals("boston")) {
            totaalPunten += 2;
            simpleMindsJuistTextView.setTextColor(Color.GREEN);
        } else {
            simpleMindsJuistTextView.setTextColor(Color.RED);
        }

        Toast toast = Toast.makeText(this, maakBericht(totaalPunten), Toast.LENGTH_SHORT);
        toast.show();

    }

    private String maakBericht(int punten) {
        return "Je behaalde " + punten + " op 10";
    }
}
