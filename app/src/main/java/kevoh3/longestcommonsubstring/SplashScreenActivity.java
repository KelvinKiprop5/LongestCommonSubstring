package kevoh3.longestcommonsubstring;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


//-----------------------------------SplashScreenActivity extends AppCompatActivity------------------------------//
public class SplashScreenActivity extends AppCompatActivity {

    //Declare variables
    private static int SPLASH_TIME_OUT = 3000; //Time in milliseconds = 1000sec

    //-----------------------------------onCreate------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        //We are using new handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Take user to main activity
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        }, SPLASH_TIME_OUT);

        //Add animation
        Animation animation= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.zoom_out);
        (findViewById(R.id.imgAnim)).startAnimation(animation);//.setAnimation(animation);

    }//-----------------------------------./onCreate------------------------------//

}//-----------------------------------./SplashScreenActivity extends AppCompatActivity------------------------------//
