package sergioescalona.dadosyvotacionesapp.Activities;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
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

import sergioescalona.dadosyvotacionesapp.JavaClasses.PreLollipopSoundPool;
import sergioescalona.dadosyvotacionesapp.R;


public class DicesActivity extends AppCompatActivity {

    private Button buttonDice;
    private Button buttonArbitrary;
    private ImageView imageViewDice;
    private TextView textViewRandom;

    Random random;              //Genera numeros al azar.
    SoundPool dice_sound;       //El sonido del dado
    int sound_id;               //Para controlar el SoundPool
    Handler handler;
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
        handler=new Handler(callback);

        //INICIAMOS EL SONIDO
        InitSound();

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
                //EMPIEZA EL SONIDO RODANDO.
                dice_sound.play(sound_id, 1.0f, 1.0f, 0, 0, 1.0f);
                //PAUSA PARA QUE LA IMAGEN SE ACTUALICE.
                //(VER LA CLASE ROLL).
                timer.schedule(new Roll(), 400);
            }
        }
    }
    //METODO PARA CREAR EL SONIDO DEL DADO.
    void InitSound() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            //POR DEFECTO PUEDE SONAR UNA VEZ.
            //TAMBIEN CONSTRUYE EL PATRÓN
            dice_sound = new SoundPool.Builder().setAudioAttributes(aa).build();

        } else {
            //CORRE EN UN DISPOSITIVO CON API ANTERIOR A LOLLIPOP
            //USA EL ANTIGUO SOUNDPOOL
            dice_sound = PreLollipopSoundPool.NewSoundPool();
        }
        //CARGA EL SONIDO.
        sound_id = dice_sound.load(this, R.raw.shake_dice, 1);
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

    //CLEAN UP
    protected void onPause() {
        super.onPause();
        dice_sound.pause(sound_id);
    }

    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}



