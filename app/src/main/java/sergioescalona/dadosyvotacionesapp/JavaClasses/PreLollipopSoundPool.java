package sergioescalona.dadosyvotacionesapp.JavaClasses;

import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Sergio on 01/06/2017.
 */
//ESTA CLASE ES PARA LOS MOVILES ANTERIORES A LOLLYPOP. DE ESTA FORMA, TAMBIEN PUEDEN ESCUCHAR EL SONIDO DEL DADO
    public final class PreLollipopSoundPool {
    @SuppressWarnings("deprecation")
    public static SoundPool NewSoundPool() {
        return new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    }
}