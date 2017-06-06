package sergioescalona.dadosyvotacionesapp.Project.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import sergioescalona.dadosyvotacionesapp.R;

public class ConfigVotationActivity extends AppCompatActivity {

    private EditText editTextTopic;
    private SeekBar seekBarParticipants;
    private TextView textViewParticipants;
    private int totalParticipants;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_votation);

        //Procedemos a instanciar los objetos de la UI y a iniciar variables.
        editTextTopic = (EditText) findViewById(R.id.editTextTopic);
        seekBarParticipants = (SeekBar) findViewById(R.id.seekBarParticipants);
        textViewParticipants = (TextView) findViewById(R.id.textViewNumberParticipants);
        ImageButton imageBotton = (ImageButton) findViewById(R.id.buttonToVotes);
        totalParticipants = 0;
        topic = null;

        //programamos el comportamiento del seekbar
        seekBarParticipants.setMax(20);
        seekBarParticipants.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentParticipants, boolean fromUser) {
                //El progreso de la barra muestra el total de participantes de la votacion.
                totalParticipants = currentParticipants;
                textViewParticipants.setText(totalParticipants + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                totalParticipants = seekBarParticipants.getProgress();
                textViewParticipants.setText(totalParticipants + "");

            }
        });

        imageBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //el topic se enviará a la activity de resultados.
                topic = editTextTopic.getText().toString();
                //si el topic es distinto de null, se inicia la siguiente actividad
                //y se pasan los datos a traves del intent.
                if (topic != "") {
                    sendConfig();
                } else {
                    Toast.makeText(ConfigVotationActivity.this, "Introduce el tema de la votación primero", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sendConfig() {

        if (totalParticipants > 0) {
            //si el numero total de participantes, es distinto de 0
            //iniciamos la siguiente activity.
            //cuando se pulsa el botón, se abre una nueva actividad y se manda a traves
            //del Intent el resultado de las votaciones.
            Intent intent = new Intent(ConfigVotationActivity.this, VoteActivity.class);
            intent.putExtra("totalParticipants", totalParticipants);
            intent.putExtra("topic", topic);
            startActivity(intent);
        } else {
            Toast.makeText(ConfigVotationActivity.this, "Introduce el número de participantes primero", Toast.LENGTH_SHORT).show();
        }
    }
}
