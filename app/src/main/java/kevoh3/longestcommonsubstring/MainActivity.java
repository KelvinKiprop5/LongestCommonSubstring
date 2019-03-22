package kevoh3.longestcommonsubstring;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

//-------------------MainActivity extends AppCompatActivity-------------------//
public class MainActivity extends AppCompatActivity {

    //Declare global variables
    private EditText etFirstString, etSecondString;
    private Button buttonReset, buttonGetSubstring;
    private TextView tvLongestSubstring;

    //-------------------onCreate------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change title
        this.setTitle("The Afrocabs Challenge");

        //Initialize global variables
        etFirstString = findViewById(R.id.etFirstText);
        etSecondString = findViewById(R.id.etSecondText);

        buttonGetSubstring = findViewById(R.id.btnGetLongestSubstring);
        buttonReset = findViewById(R.id.btnReset);

        tvLongestSubstring = findViewById(R.id.tvLongestSubstring);


        //Handle buttons click
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear
                etFirstString.setText("");
                etSecondString.setText("");
                tvLongestSubstring.setText("");
            }
        });

        buttonGetSubstring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fString = etFirstString.getText().toString();
                String sString = etSecondString.getText().toString();

                if (fString.isEmpty()) {
                    TastyToast.makeText(MainActivity.this, "First text field is empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                } else if (sString.isEmpty()) {
                    TastyToast.makeText(MainActivity.this, "Second text field is empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                } else {

                    //Display common string
                    if (getLongestCommonString(fString,sString).isEmpty()){
                        tvLongestSubstring.setText("No common substring");
                        tvLongestSubstring.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorRed));
                        TastyToast.makeText(MainActivity.this, "No common substring", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    }else {
                        tvLongestSubstring.setText(getLongestCommonString("."+fString,"."+sString));
                        tvLongestSubstring.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorGreen));
                        TastyToast.makeText(MainActivity.this, "The common substring is:\n"+getLongestCommonString("."+fString,"."+sString), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    }

                }
            }

        });


    }//-------------------./onCreate------------------//



    //-------------------longestCommonString------------------//
    private String getLongestCommonString(String string1, String string2) {

        int string1_length = string1.length();
        int string2_length = string2.length();
        final int pick_string1_or_string2 = 0;
        final int pick_string1 = 1;
        final int pick_string2 = 2;
        int match[][] = new int[string1_length][string2_length];
        int pointer[][]  = new int[string1_length][string2_length];

        for(int i=0; i<string1_length; i++)
        {
            for(int j=0; j<string2_length; j++)
            {
                //Get matching characters
                    if(string1.charAt(i) ==  string2.charAt(j))
                {
                    if((i==0) || (j==0)) //first row or first column
                    {
                        match[i][j] = 1; //initialize
                    }
                    else
                    {
                        match[i][j] = match[i-1][j-1] + 1;
                    }
                    pointer[i][j] = pick_string1_or_string2;
                }
                else  //Characters mismatch
                {
                    if((i > 0 ) && (j > 0)) //neither the first row nor first column
                    {

                        if(match[i-1][j] >= match[i][j-1])
                        {
                            match[i][j] = match[i-1][j];
                            pointer[i][j] = pick_string2; //ditch s1's character
                        }
                        else
                        {
                            match[i][j] = match[i][j-1];
                            pointer[i][j] = pick_string1;//ditch s2's character.
                        }
                    }
                    else if ((i==0) && ( j > 0)) //first row
                    {
                        match[i][j] = match[i][j-1];
                        pointer[i][j] = pick_string1;
                    }
                    else if((j==0) && (i>0)) //first column

                    {
                        match[i][j] = match[i-1][j];
                        pointer[i][j] = pick_string2;
                    }

                }

            }
        }


        //Printing the LCS.
        int i = string1_length - 1;
        int j = string2_length - 1;
        StringBuffer result = new StringBuffer();

        while(i>0 && j>0)
        {
            switch(pointer[i][j])
            {
                //go diagonal and collect the matched character
                case pick_string1_or_string2:
                    result.append(string1.charAt(i));
                    i--;
                    j--;
                    break;
                case pick_string1://go left
                    j--;
                    break;
                case pick_string2://go up
                    i--;
                    break;
            }
        }

        return String.valueOf(result.reverse());



    }//-------------------./longestCommonString------------------//


}//-------------------./MainActivity extends AppCompatActivity------------------//
