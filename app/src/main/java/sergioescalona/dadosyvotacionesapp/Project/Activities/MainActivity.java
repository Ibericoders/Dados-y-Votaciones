package sergioescalona.dadosyvotacionesapp.Project.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sergioescalona.dadosyvotacionesapp.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonDice;
    private Button buttonVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSTANCIAMOS LOS BOTONES.
        buttonDice = (Button) findViewById(R.id.buttonDice);
        buttonVote = (Button) findViewById(R.id.buttonVotes);



    }

    //PROGRAMAMOS EL COMPORTAMIENTO DE LOS BOTONES AL HACER CLICK.
    public void goToDice(View view) {
        Intent intent=new Intent(this,DicesActivity.class);
        this.startActivity(intent);
    }

    public void goToVote(View view) {
        Intent intent=new Intent(this,VoteActivity.class);
        this.startActivity(intent);
    }
}
