package sergioescalona.dadosyvotacionesapp.Project.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import sergioescalona.dadosyvotacionesapp.R;

public class VoteActivity extends AppCompatActivity {

    private EditText editTextTopic;
    private SeekBar seekBarParticipants;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private Button buttonResult;
    private TextView textViewParticipants;
    private TextView textViewHowMany;

    //creamos variables numericas para contar cuántos votos tiene cada opcion
    private int votesInOptionA;
    private int votesInOptionB;
    private int votesInOptionC;
    private int votesInOptionD;
    private int totalParticipants;
    private int totalVotesInOptions;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        //Procedemos a instanciar los objetos de la UI y a iniciar variables.
        editTextTopic = (EditText) findViewById(R.id.editTextTopic);
        seekBarParticipants = (SeekBar) findViewById(R.id.seekBarParticipants);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        buttonResult = (Button) findViewById(R.id.buttonResult);
        textViewParticipants = (TextView) findViewById(R.id.textViewNumberParticipants);
        textViewHowMany = (TextView) findViewById(R.id.textViewParticipants);

        //iniciamos las variables numericas en 0;
        votesInOptionA = 0;
        votesInOptionB = 0;
        votesInOptionC = 0;
        votesInOptionD = 0;
        totalParticipants = 0;
        //necesitamos esta variable para saber el total de votos que se llevan.
        totalVotesInOptions = 0;

        //programamos el comportamiento del seekbar
        seekBarParticipants.setMax(10);
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


        //ponemos el boton de resultado invisible, y el resto visibles.
        buttonResult.setVisibility(View.INVISIBLE);

        //programamos el comportamiento de los botones
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cada click, añade un voto a la variable correspondiente, pero sólo si
                // el numero de participantes se ha definido antes y es mayor que 0.

                if (totalParticipants > 0) {
                    votesInOptionA++;
                    totalVotesInOptions++;
                    setVisibilityResult();

                } else {
                    Toast.makeText(VoteActivity.this, "Introduce el número de participantes primero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalParticipants > 0) {
                    votesInOptionB++;
                    totalVotesInOptions++;
                    setVisibilityResult();
                } else {
                    Toast.makeText(VoteActivity.this, "Introduce el número de participantes primero", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalParticipants > 0) {
                    votesInOptionC++;
                    totalVotesInOptions++;
                    setVisibilityResult();
                } else {
                    Toast.makeText(VoteActivity.this, "Introduce el número de participantes primero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalParticipants > 0) {
                    votesInOptionD++;
                    totalVotesInOptions++;
                    setVisibilityResult();
                } else {
                    Toast.makeText(VoteActivity.this, "Introduce el número de participantes primero", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void setVisibilityResult() {
        //le metemos un retardo de 0.5 segundos.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (totalVotesInOptions == totalParticipants && totalVotesInOptions != 0) {
            //si el numero total de votos es igual al de participantes, y distinto de 0
            //iniciamos la siguiente activity.
            //el topic se enviará a la activity de resultados.
            topic = editTextTopic.getText().toString();
            //cuando se pulsa el botón, se abre una nueva actividad y se manda a traves
            //del Intent el resultado de las votaciones.
            Intent intent = new Intent(VoteActivity.this, ResultActivity.class);
            intent.putExtra("votesA", votesInOptionA);
            intent.putExtra("votesB", votesInOptionB);
            intent.putExtra("votesC", votesInOptionC);
            intent.putExtra("votesD", votesInOptionD);
            intent.putExtra("totalParticipants", totalParticipants);
            intent.putExtra("topic", topic);
            startActivity(intent);

        }
    }

}

