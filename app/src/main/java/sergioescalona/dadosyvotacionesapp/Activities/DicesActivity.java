package sergioescalona.dadosyvotacionesapp.Activities;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sergioescalona.dadosyvotacionesapp.R;


public class DicesActivity extends AppCompatActivity {

    private Button buttonDice;
    private Button buttonArbitrary;
    private ImageView imageViewDice;
    private TextView textViewRandom;

    Random random;              //Genera numeros al azar.
    Handler handler;            //Manejador para el TimerTask
    Timer timer;                //Usado para darle feedback al usuario.
    boolean rolling;            //Está el dado rodando?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dices);

        //INSTANCIAMOS LOS ELEMENTOS DE LA UI Y OTROS.

        buttonDice = (Button) findViewById(R.id.buttonDice);
        buttonArbitrary = (Button) findViewById(R.id.buttonArbitrary);
        imageViewDice = (ImageView) findViewById(R.id.imageViewDice);
        textViewRandom = (TextView) findViewById(R.id.textViewArbitraryNumber);
        random = new Random();
        rolling = false;
        timer = new Timer();

        buttonDice.setOnClickListener(new HandleClick());
        //ENLACE DEL HANDLER Y EL CALLBACK.
        handler = new Handler(callback);


        //CREAMOS UN LISTENER PARA EL BOTON DE ALEATORIO
        buttonArbitrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GENERAMOS UN NUMERO DE 1 A 10 ALEATORIO.
                int randomNumber = (int) (Math.random() * 10 + 1);
                //VOLCAMOS ESE NUMERO EN EL TEXTVIEW.
                textViewRandom.setText(String.valueOf(randomNumber));
            }
        });

    }

    //EL USUARIO HA PULSADO EL BOTON Y EL DADO RUEDA
    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            //SI NO ESTÁ RODANDO YA, CLARO.
            if (!rolling) {
                rolling = true;
                //ENSEÑAMOS EL DADO RODANDO.
                imageViewDice.setImageResource(R.drawable.dice3droll);
                //PAUSA PARA QUE LA IMAGEN SE ACTUALICE.
                //(VER LA CLASE ROLL).
                timer.schedule(new Roll(), 400);
            }
        }
    }

    //CUANDO ACABA ENVIA UN MENSAJE DE CALLBACK.
    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    //RECIBE UN MENSAJE DEL TIMER DE QUE SE PUEDE INICIAR.
    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //OBTENEMOS EL RESULTADO DEL DADO
            //NEXTINT VA DE 0 A 5, POR TANTO, +1.
            switch (random.nextInt(6) + 1) {
                case 1:
                    imageViewDice.setImageResource(R.drawable.one);
                    break;
                case 2:
                    imageViewDice.setImageResource(R.drawable.two);
                    break;
                case 3:
                    imageViewDice.setImageResource(R.drawable.three);
                    break;
                case 4:
                    imageViewDice.setImageResource(R.drawable.four);
                    break;
                case 5:
                    imageViewDice.setImageResource(R.drawable.five);
                    break;
                case 6:
                    imageViewDice.setImageResource(R.drawable.six);
                    break;
                default:
            }
            rolling = false;  //EL USUARIO PUEDE VOLVER A TIRAR PORQUE HA ACABADO LA TIRADA.
            return true;
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}



