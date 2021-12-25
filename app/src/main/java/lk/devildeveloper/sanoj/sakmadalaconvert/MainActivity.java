package lk.devildeveloper.sanoj.sakmadalaconvert;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import com.pixplicity.easyprefs.library.Prefs;
import com.sdsmdg.tastytoast.TastyToast;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.varunjohn1990.iosdialogs4android.IOSDialog;
import lk.devildeveloper.sanoj.sakmadalaconvert.htmldialog.HtmlDialog;
import lk.devildeveloper.sanoj.sakmadalaconvert.htmldialog.HtmlDialogListener;
import lk.sakmadala.sakmadala;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class MainActivity extends AppCompatActivity {

   private EditText unicode;
   private EditText Legacy;
   private SeekBar upunicode;
   private CheckBox views;
   private ClipboardManager myClipboard;
   private ClipData myClip;
   private Typeface Legacys;
   private Typeface arial;
    private String linkexample;
    private Button erazers;
    private Toolbar toolbar;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private GuideView mGuideView;
    private GuideView.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();



        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("sAk|mAdl...");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Legacys = Typeface.createFromAsset(getAssets(),"fonts/sak.otf");
        arial = Typeface.createFromAsset(getAssets(),"fonts/arial.ttf");

        unicode = (EditText) findViewById(R.id.unicode);
        Legacy = (EditText) findViewById(R.id.ligacy);
        Legacy.setEnabled(false);
        Legacy.setTypeface(Legacys);
        erazers = findViewById(R.id.erazer);
        /////////////////////////////////////////////////////////////////

        Typeface unicodes = Typeface.createFromAsset(getAssets(),
                "fonts/emanee.ttf");
        unicode.setTypeface(unicodes);
        upunicode = (SeekBar) findViewById(R.id.seekBar);
        upunicode.setMax(100);
        upunicode.setProgress((int) 22);

        upunicode.setOnSeekBarChangeListener(new mylestner());


        unicodechanger();

        view1 = findViewById(R.id.firstlayout);
        view2 = findViewById(R.id.firstlayout2);
        view3 = findViewById(R.id.seekBar);
        view4 = findViewById(R.id.erazer);
        view5 = findViewById(R.id.checkBox);
        view6 = findViewById(R.id.toolbar);


        try{
            String data = Prefs.getString("frist", "1sttime");
            if(data.equals("1sttime"))   {
                ShowPolicy();
            }else{
                //disable Showcase
            }

        }catch (Exception IOP){

        }

        erazers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unicode.setText("");
                Legacy.setText("");
                Toast.makeText(getApplicationContext(), "Clear", Toast.LENGTH_SHORT).show();
            }
        });
        views = (CheckBox) findViewById(R.id.checkBox);
        views.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(buttonView.isChecked()){
                    Legacy.setTypeface(arial);
                }else {
                    Legacy.setTypeface(Legacys);
                }

            }
        }
        );

        unicode.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //no-need
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
                //no-need
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0)
                    unicodechanger();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem items) {
        switch (items.getItemId()) {
            case  R.id.imageButton:

                adsshow();

                break;
            case R.id.action_refresh:
                new FancyGifDialog.Builder(this)
                        .setTitle("What's going on with this?")
                        .setMessage("\n" +
                                "The main purpose of this software is to adapt the Unicode fonts we are currently using to fit Sakmadala fonts so that you can use any font you like when editing photos on your smartphone.\n")
                        .setNegativeBtnText("View FB")
                        .setPositiveBtnBackground("#FF4081")
                        .setPositiveBtnText("Ok")
                        .setNegativeBtnBackground("#FFA9A7A8")
                        .setGifResource(R.drawable.gif12)   //Pass your Gif here
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {

                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                String url = "https://www.facebook.com/sanoj.jayathilaka1";

                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                        })
                        .build();
                break;

            case R.id.developer:

                new FancyGifDialog.Builder(this)
                        .setTitle("This app By Devil Developer")
                        .setMessage("1.0(App: Alpa / Typeface :Beta)\n" +
                                "Developer Sanoj Prabath\n" +
                                "FB - sanoj.jayathilaka1\n" +
                                "Github- 00sanoj00")

                        .setNegativeBtnText("View FB")
                        .setPositiveBtnBackground("#FF4081")
                        .setPositiveBtnText("Ok")
                        .setNegativeBtnBackground("#FFA9A7A8")
                        .setGifResource(R.drawable.gif12)   //Pass your Gif here
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {

                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                String url = "https://www.facebook.com/sanoj.jayathilaka1";

                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                        })
                        .build();

                break;

            case R.id.tut:
                showcasig1();
                break;
            default:
                break;
        }

        return true;
    }
    void showadsandcopy(){

        copymytext();
        TastyToast.makeText(getApplicationContext(), "Text Copied !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
        Legacy.setEnabled(true);


    }
    private class mylestner implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            unicode.setTextSize(upunicode.getProgress());
            Legacy.setTextSize(upunicode.getProgress());//arial.ttf
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    public void unicodechanger(){
        String sakmadalaout = sakmadala.sakmadalatext(unicode.getText().toString());
        Legacy.setText(sakmadalaout);
    }

    public void copymytext(){
        String uni = unicode.getText().toString();
        String clipiniya = Legacy.getText().toString();
        ClipboardManager clipbord = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Text",clipiniya);
        clipbord.setPrimaryClip(clip);
    }
    public void ShowPolicy(){
        new HtmlDialog.Builder(getSupportFragmentManager())
                .setListener(listener)
                .setHtmlResId(R.raw.policy)
                .setTitle(getResources().getString(R.string.title))
                .setShowPositiveButton(true)
                .setPositiveButtonText(getResources().getString(R.string.ok))
                .build()
                .show();
    }
    private HtmlDialogListener listener = new HtmlDialogListener() {
        @Override
        public void onNegativeButtonPressed() {
            unagree();
        }
        @Override
        public void onPositiveButtonPressed() {
            showcasig1();
        }
    };
    void showcasig1(){
        builder = new GuideView.Builder(this)
                .setTitle("Enter unicode characters here")
                .setContentText("Enter Unicode characters here\n  Unicode characters can be typed in any form here\n just typing or copying and pasting is enough")
                .setGravity(Gravity.center)
                .setDismissType(DismissType.anywhere)
                .setTargetView(view1)
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        switch (view.getId()) {
                            case R.id.firstlayout:
                                builder.setTargetView(view2).build();
                                builder.setTitle("Sakmadala font view area");
                                builder.setContentText("What you write above will immediately become \n Legacy letters and appear in this section");
                                break;
                            case R.id.firstlayout2:
                                builder.setTargetView(view3).build();
                                builder.setTitle("Changing the size");
                                builder.setContentText("Increasing or decreasing this will make it easier to change the font size");
                                break;
                            case R.id.seekBar:
                                builder.setTargetView(view4).build();
                                builder.setTitle("Eraser");
                                builder.setContentText("Clicking this once will erase all what you wrote");
                                break;
                            case R.id.erazer:
                                builder.setTargetView(view5).build();
                                builder.setTitle("What the Legacy font looks like");
                                builder.setContentText("This will give you an idea of how Legacy characters are used");
                                break;
                            case R.id.checkBox:
                                builder.setTargetView(view6).build();
                                builder.setTitle("Toolbar");
                                builder.setContentText("With the click of a button here you can copy the translated words with one click");
                                break;
                            case R.id.toolbar:
                                Prefs.putString("frist", "2ndtime");
                                TastyToast.makeText(getApplicationContext(), "Tutorial Over", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                                return;

                        }
                        mGuideView = builder.build();
                        mGuideView.show();
                    }
                });

        mGuideView = builder.build();
        mGuideView.show();
        updatingForDynamicLocationViews();
    }
    private void updatingForDynamicLocationViews() {
        view4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mGuideView.updateGuideViewLocation();
            }
        });
    }
    void unagree(){
        new IOSDialog.Builder(MainActivity.this)
                .title("Do you agree with this?")
                .message("If you do not agree, you can no longer use this application")
                .positiveButtonText("No, sure")
                .positiveClickListener(new IOSDialog.Listener() {
                    @Override
                    public void onClick(IOSDialog iosDialog) {
                        iosDialog.dismiss();
                        finish();
                    }
        })
         .build().show();
    }

    void adsshow(){
        new IOSDialog.Builder(MainActivity.this)
                .title("Would you like to ?")              // String or String Resource ID
                .message("Copying begins after the ad, would you like to copy this?")  // String or String Resource ID
                .positiveButtonText("Yeah, sure")  // String or String Resource ID
                .negativeButtonText("No Thanks")   // String or String Resource ID
                .positiveClickListener(new IOSDialog.Listener() {
                    @Override
                    public void onClick(IOSDialog iosDialog) {
                        iosDialog.dismiss();
                        showadsandcopy();

                    }
                }).negativeClickListener(new IOSDialog.Listener() {
            @Override
            public void onClick(IOSDialog iosDialog) {
                iosDialog.dismiss();
            }
        })
                .build()
                .show();
    }

}

