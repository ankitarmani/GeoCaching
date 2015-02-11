package com.geoquest.android;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * .
 * @author Fani .
 */
public class QuestionActivity extends Activity {
	
	/**
     * .
     */
	private static final int QUESTION_LENGTH = 9;
	/**
     * .
     */
	private static final int ANSWER_LENGTH = 10;
    /**
     * .
     */
    private int [] positionLongi = new int [QUESTION_LENGTH];
    /**
     * .
     */
    private String [] questionLongi = new String [QUESTION_LENGTH];
    /**
     * .
     */
    private int [] positionLati = new int [QUESTION_LENGTH];
    /**
     * .
     */
    private String [] questionLati = new String [QUESTION_LENGTH];
    /**
     * .
     */
    private TextView qestionText;
    /**
     * .
     */
    private Button btnSubmitAnswers;
    /**
     * .
     */
    private static final int INVISIBLE = 4;
    /**
     * .
     */
    private static final String TAG_LAT = "resultLat";
    /**
     * .
     */
    private static final String TAG_LNG = "resultLng";
    /**
     * .
     */
    private static final String TAG_COUNTER = "counter";
    /**
     * .
     */
    private int counter;
    /**
     * .
     */
    private int orderPosition;
    /**
     * .
     */
    private MySQLiteHelper db = new MySQLiteHelper(this);
    /**
     * .
     */
    Button buttonslng[] = new Button[QUESTION_LENGTH];
    /**
     * .
     */
    Button buttonslat[] = new Button[QUESTION_LENGTH];
    /**
     * .
     */
    Button buttonsAnswer[] = new Button[ANSWER_LENGTH];
    
    /**
     * .
     * @param savedInstanceState .
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        Bundle extras = getIntent().getExtras();
        counter = extras.getInt(TAG_COUNTER);
        //counter_wp = Integer.parseInt(extras.getString(TAG_COUNTER_WP));
        orderPosition = extras.getInt("orderPosition");
        orderPosition += 1;
        counter -= 1;

        //Log.d("counter in question",Integer.toString(counter));
        Log.d("orderposition in question", Integer.toString(orderPosition));

        final String latsql = db.getLatitude(orderPosition);
        final String lngsql = db.getLongitude(orderPosition);
        Log.d("orderposition in question2", Integer.toString(orderPosition));

        List<Question> questionLongitude = db.getLongitudeQuestion(
        		orderPosition);       
        int i = 0;
        for (Question cn : questionLongitude) {
            String log = "Position: " + cn.getDigitPosition() 
            		+ " ,question: " + cn.getQuestion();
                // Writing Contacts to log
            Log.d("Name: ", log);
            //int test=cn.getDigitPosition();
            //String test1=cn.getQuestion();            
            positionLongi[i] = cn.getDigitPosition();
            questionLongi[i] = cn.getQuestion();
        Log.d("test position", Integer.toString(positionLongi[i]));
        Log.d("test question", questionLongi[i]);
        i++; }

        List<Question> questionLatitude = db.getLatitudeQuestion(
        		orderPosition);       
        int j = 0;
        for (Question cn : questionLatitude) {
            String log = "Position: " + cn.getDigitPosition() 
            		+ " ,question: " + cn.getQuestion();
                // Writing Contacts to log
            Log.d("Name: ", log);
            //int test=cn.getDigitPosition();
            //String test1=cn.getQuestion();            
            positionLati[j] = cn.getDigitPosition();
            questionLati[j] = cn.getQuestion();
        Log.d("test position", Integer.toString(positionLati[j]));
        Log.d("test question", questionLati[j]);
        j++;
        }

        Log.d("test question empty", questionLongi[0]);


        qestionText = (TextView) findViewById(R.id.question_text);
        int[] buttonIDs = new int[] {R.id.question0, 
        		R.id.question1, R.id.question2, 
        		R.id.question3, R.id.question4, 
        		R.id.question5, R.id.question6,
        		R.id.question7, R.id.question8};
        
        int[] buttonIDLats = new int[] {R.id.questionlat0, 
        		R.id.questionlat1, R.id.questionlat2, 
        		R.id.questionlat3, R.id.questionlat4, 
        		R.id.questionlat5, R.id.questionlat6,
        		R.id.questionlat7, R.id.questionlat8};
        
        int[] buttonIDanswer = new int[] {R.id.answer_a, 
        		R.id.answer_b, R.id.answer_c, 
        		R.id.answer_d, R.id.answer_e, 
        		R.id.answer_f, R.id.answer_g,
        		R.id.answer_h, R.id.answer_i, R.id.answer_j};
        
        
        for (int t = 0; t < QUESTION_LENGTH; t++) {
        	   buttonslng[t] = new Button(this);
        	   buttonslng[t] = (Button) findViewById(buttonIDs[t]);
        	   buttonslng[t].setText(lngsql.substring(t, t + 1));
        	   buttonslng[t].setTextColor(Color.WHITE);
        	   buttonslng[t].setBackgroundColor(Color.DKGRAY);
        	   buttonslng[t].setEnabled(false);
        	   
        	   buttonslat[t] = new Button(this);
         	   buttonslat[t] = (Button) findViewById(buttonIDLats[t]);
         	   buttonslat[t].setText(latsql.substring(t, t + 1));
         	   buttonslat[t].setTextColor(Color.WHITE);
         	   buttonslat[t].setBackgroundColor(Color.DKGRAY);
         	   buttonslat[t].setEnabled(false);
        	   
        }
        
        for (int t = 0; t < ANSWER_LENGTH; t++) {
        	   buttonsAnswer[t] = new Button(this);
        	   buttonsAnswer[t] = (Button) findViewById(buttonIDanswer[t]);
        	   buttonsAnswer[t].setVisibility(INVISIBLE);
        	
        }
        

        for (int s = 0; s < QUESTION_LENGTH; s++) {
        	if (questionLongi[s] != null && !questionLongi[s].equals("")) {
        		buttonslng[s].setText("?");
        		buttonslng[s].setEnabled(true);
        		buttonslng[s].setBackgroundColor(Color.BLACK);
        	}
        	
        	if (questionLati[s] != null && !questionLati[s].equals("")) {
        		buttonslat[s].setText("?");
        		buttonslat[s].setEnabled(true);
        		buttonslat[s].setBackgroundColor(Color.BLACK);
        	}
        	
        }

        btnSubmitAnswers = (Button) findViewById(R.id.btnSubmitAnswers);

        btnSubmitAnswers.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

            	String answerlng = "";
            	String answerlat = "";
            	for (int j = 0; j < QUESTION_LENGTH; j++) {
            		answerlng += buttonslng[j].getText().toString();
            		answerlat += buttonslat[j].getText().toString();
            	}
            	
            	int questionmark = 0;
            	for (int i = 0; i < QUESTION_LENGTH; i++) {
            	    if (answerlng.charAt(i) == '?' 
            	    		|| answerlat.charAt(i) == '?') {
            	    	questionmark++;
            	    }
            	}
            	Log.d("test submit", answerlng);
            	//Pattern p = Pattern.compile("(?)");
            	//Matcher m = p.matcher(answerlng);
            	//Matcher n = p.matcher(answerlat);
            	
            	if (questionmark > 0) {
					Toast.makeText(QuestionActivity.this,
            				"Please answer all the question!", 
            				Toast.LENGTH_LONG).show(); 
					} else {
            	int a = 0; 
            	String changedLngDigits = buttonslng[a].getText().toString() 
            			+ buttonslng[++a].getText().toString() 
            			+ buttonslng[++a].getText().toString()
            			+ buttonslng[++a].getText().toString()
            			+ buttonslng[++a].getText().toString()
            			+ buttonslng[++a].getText().toString()
            			+ buttonslng[++a].getText().toString()
            			+ buttonslng[++a].getText().toString()
            			+ buttonslng[++a].getText().toString();
            	String resultLng = lngsql.replace(lngsql.substring(0, ++a), 
            			changedLngDigits);

            	int b = 0;
            	String changedLatDigits = buttonslat[b].getText().toString() 
            			+ buttonslat[++b].getText().toString() 
            			+ buttonslat[++b].getText().toString()
            			+ buttonslat[++b].getText().toString()
            			+ buttonslat[++b].getText().toString()
            			+ buttonslat[++b].getText().toString()
            			+ buttonslat[++b].getText().toString()
            			+ buttonslat[++b].getText().toString()
            			+ buttonslat[++b].getText().toString();
            	String resultLat = latsql.replace(latsql.substring(0, ++b),
            			changedLatDigits);

            	Log.d("resultLat", resultLat);
            	Log.d("resultLng", resultLng);        	

                Intent i = new Intent(getApplicationContext(),
                		NaviActivity.class);
                i.putExtra(TAG_LAT, resultLat);
                i.putExtra(TAG_LNG, resultLng);
                i.putExtra(TAG_COUNTER, counter);
                i.putExtra("orderPosition", orderPosition);
               // i.putExtra(TAG_COUNTER_WP, --counter_wp);
                startActivity(i);
                finish();
                }
            }
        });

        for (int m = 0; m < QUESTION_LENGTH; m++) {
        	final int index = m;
        	buttonslng[m].setOnClickListener(new View.OnClickListener() {
        		public void onClick(final View view) {
        			qestionText.setText(questionLongi[index]);
        			
        			for (int t = 0; t < ANSWER_LENGTH; t++) {
        				buttonsAnswer[t].setVisibility(0);
        				buttonsAnswer[t].setText(Integer.toString(t));

        			final int indexanswer = t;
        			buttonsAnswer[t].setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(final View v) {
                        	
                        	for (int l = 0; l < ANSWER_LENGTH; l++) {
                				buttonsAnswer[l].setVisibility(INVISIBLE); 
                				 }
                        	
                            qestionText.setText("");
                            buttonslng[index].setText(
                            		buttonsAnswer[indexanswer].getText());
                            buttonslng[index].setTextColor(Color.WHITE);
                            buttonslng[index].setBackgroundColor(Color.DKGRAY);
                        }
                    });
        		}
        			
        		}
        	});
        	
        	buttonslat[m].setOnClickListener(new View.OnClickListener() {
        		public void onClick(final View view) {
        			qestionText.setText(questionLati[index]);
        			
        			for (int t = 0; t < ANSWER_LENGTH; t++) {
        				buttonsAnswer[t].setVisibility(0);
        				buttonsAnswer[t].setText(Integer.toString(t));
        			

        			final int indexanswer = t;
        			buttonsAnswer[t].setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(final View v) {
                        	
                        	for (int l = 0; l < ANSWER_LENGTH; l++) {
                				buttonsAnswer[l].setVisibility(INVISIBLE); 
                				}
                            qestionText.setText("");
                            buttonslat[index].setText(
                            		buttonsAnswer[indexanswer].getText());
                            buttonslat[index].setTextColor(Color.WHITE);
                            buttonslat[index].setBackgroundColor(Color.DKGRAY);
                        }
                    });
        		}
        			
        		}
        	});
        }
        
    }
}