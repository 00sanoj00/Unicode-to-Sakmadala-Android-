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
        String text = unicode.getText().toString();
        text = text.replace(",", ",");
        text = text.replace(".", ".");
        text = text.replace("ත්‍රෛ", "t|rAI");//
        text = text.replace("ශෛ", "xAI");//
        text = text.replace("චෛ", "cAI");//
        text = text.replace("ජෛ", "jAI");//
        text = text.replace("කෛ", "kAI");//
        text = text.replace("මෛ", "mAI");//
        text = text.replace("පෛ", "pAI");//
        text = text.replace("දෛ", "zAI");//
        text = text.replace("තෛ", "wAI");//
        text = text.replace("නෛ", "nAI");//
        text = text.replace("ධෛ", "ZAI");//
        text = text.replace("වෛ", "vAI");//
        text = text.replace("ප්‍රෞ", "fm%!");
        text = text.replace("ෂ්‍යෝ", "fIHda");
        text = text.replace("ඡ්‍යෝ", "fPHda");
        text = text.replace("ඪ්‍යෝ", "fVHda");
        text = text.replace("ඝ්‍යෝ", "f>Hda");
        text = text.replace("ඛ්‍යෝ", "fLHda");
        text = text.replace("ළ්‍යෝ", "f<Hda");
        text = text.replace("ඵ්‍යෝ", "fMHda");
        text = text.replace("ඨ්‍යෝ", "fGHda");
        text = text.replace("ශ්‍යෝ", "fYHda");
        text = text.replace("ක්‍ෂ්‍යෝ", "fÌHda");
        text = text.replace("බ්‍යෝ", "fnHda");
        text = text.replace("ච්‍යෝ", "fpHda");
        text = text.replace("ඩ්‍යෝ", "fâHda");
        text = text.replace("ෆ්‍යෝ", "f*Hda");
        text = text.replace("ග්‍යෝ", "f.Hda");
        text = text.replace("ජ්‍යෝ", "fcHda");
        text = text.replace("ක්‍යෝ", "flHda");
        text = text.replace("ල්‍යෝ", "f,Hda");
        text = text.replace("ම්‍යෝ", "fuHda");
        text = text.replace("න්‍යෝ", "fkHda");
        text = text.replace("ප්‍යෝ", "fmHda");
        text = text.replace("ද්‍යෝ", "foHda");
        text = text.replace("ස්‍යෝ", "fiHda");
        text = text.replace("ට්‍යෝ", "fgHda");
        text = text.replace("ව්‍යෝ", "fjHda");
        text = text.replace("ත්‍යෝ", "f;Hda");
        text = text.replace("භ්‍යෝ", "fNHda");
        text = text.replace("ධ්‍යෝ", "fOHda");
        text = text.replace("ථ්‍යෝ", "f:Hda");
        text = text.replace("ෂ්‍යො", "fIHd");
        text = text.replace("ශ්‍යො", "fYHd");
        text = text.replace("ඛ්‍යො", "fLHd");
        text = text.replace("ක්‍ෂ්‍යො", "fÌHd");
        text = text.replace("බ්‍යො", "fnHd");
        text = text.replace("ව්‍යො", "fjHd");
        text = text.replace("ඩ්‍යො", "fvHd");
        text = text.replace("ෆ්‍යො", "f*Hd");
        text = text.replace("ග්‍යො", "f.Hd");
        text = text.replace("ජ්‍යො", "fcHd");
        text = text.replace("ක්‍යො", "flHd");
        text = text.replace("ම්‍යො", "fuHd");
        text = text.replace("ප්‍යො", "fmHd");
        text = text.replace("ද්‍යො", "foHd");
        text = text.replace("ස්‍යො", "fiHd");
        text = text.replace("ට්‍යො", "fgHd");
        text = text.replace("ව්‍යො", "fjHd");
        text = text.replace("ත්‍යො", "f;Hd");
        text = text.replace("භ්‍යො", "fNHd");
        text = text.replace("ධ්‍යො", "fOHd");
        text = text.replace("ථ්‍යො", "f:Hd");
        text = text.replace("ෂ්‍යෙ", "fIH");
        text = text.replace("ඡ්‍යෙ", "fPH");
        text = text.replace("ළ්‍යෙ", "f<H");
        text = text.replace("ණ්‍යෙ", "fKH");
        text = text.replace("ච්‍යෙ", "fpH");
        text = text.replace("ල්‍යෙ", "f,H");
        text = text.replace("න්‍යෙ", "fkH");
        text = text.replace("ශ්‍යෙ", "fYH");
        text = text.replace("ඛ්‍යෙ", "fLH");
        text = text.replace("ක්‍ෂ්යෙ", "fÌH");
        text = text.replace("බ්‍යෙ", "fnH");
        text = text.replace("ඩ්‍යෙ", "fvH");
        text = text.replace("ෆ්‍යෙ", "f*H");
        text = text.replace("ග්‍යෙ", "f.H");
        text = text.replace("ජ්‍යෙ", "fcH");
        text = text.replace("ක්‍යෙ", "flH");
        text = text.replace("ම්‍යෙ", "fuH");
        text = text.replace("ප්‍යෙ", "fmH");
        text = text.replace("ද්‍යෙ", "foH");
        text = text.replace("ස්‍යෙ", "fiH");
        text = text.replace("ට්‍යෙ", "fgH");
        text = text.replace("ව්‍යෙ", "fjH");
        text = text.replace("ත්‍යෙ", "f;H");
        text = text.replace("භ්‍යෙ", "fNH");
        text = text.replace("ධ්‍යෙ", "fOH");
        text = text.replace("ථ්‍යෙ", "f:H");
        text = text.replace("ෂ්‍රෝ", "fI%da");
        text = text.replace("ඝ්‍රෝ", "f>%da");
        text = text.replace("ශ්‍රෝ", "fY%da");
        text = text.replace("ක්‍ෂ්‍රෝ", "fÌ%da");
        text = text.replace("බ්‍රෝ", "fn%da");
        text = text.replace("ඩ්‍රෝ", "fv%da");
        text = text.replace("ෆ්‍රෝ", "f*%da");
        text = text.replace("ග්‍රෝ", "f.%da");
        text = text.replace("ක්‍රෝ", "fl%da");
        text = text.replace("ප්‍රෝ", "fm%da");
        text = text.replace("ද්‍රෝ", "føda");
        text = text.replace("ස්‍රෝ", "fi%da");
        text = text.replace("ට්‍රෝ", "fg%da");
        text = text.replace("ත්‍රෝ", "f;%da");
        text = text.replace("ශ්‍රො", "fY%d");
        text = text.replace("ඩ්‍රො", "fv%d");
        text = text.replace("ෆ්‍රො", "f*%d");
        text = text.replace("ග්‍රො", "f.%d");
        text = text.replace("ක්‍රො", "fl%d");
        text = text.replace("ප්‍රො", "fm%d");
        text = text.replace("ද්‍රො", "fød");
        text = text.replace("ස්‍රො", "fi%d");
        text = text.replace("ට්‍රො", "fg%d");
        text = text.replace("ත්‍රො", "f;%d");
        text = text.replace("ශ්‍රේ", "fYa%");
        text = text.replace("බ්‍රේ", "fí%");
        text = text.replace("ඩ්‍රේ", "fâ%");
        text = text.replace("ෆ්‍රේ", "f*a%");
        text = text.replace("ග්‍රේ", "f.a%");
        text = text.replace("ක්‍රේ", "fla%");
        text = text.replace("ප්‍රේ", "fma%");
        text = text.replace("ද්‍රේ", "føa");
        text = text.replace("ස්‍රේ", "fia%");
        text = text.replace("ත්‍රේ", "f;a%");
        text = text.replace("ධ්‍රේ", "fè%");
        text = text.replace("ෂ්‍රෙ", "fI%");
        text = text.replace("ශ්‍රෙ", "fY%");
        text = text.replace("බ්‍රෙ", "fn%");
        text = text.replace("ෆ්‍රෙ", "f*%");
        text = text.replace("ග්‍රෙ", "f.%");
        text = text.replace("ක්‍රෙ", "fl%");
        text = text.replace("ප්‍රෙ", "fm%");
        text = text.replace("ප්\u200Dර", "p|U");//new add
        text = text.replace("ශ්\u200Dරී", "x|U");//new add
        text = text.replace("ද්‍රෙ", "fø");
        text = text.replace("ස්‍රෙ", "fi%");
        text = text.replace("ත්‍රෙ", "f;%");
        text = text.replace("භ්‍රෙ", "fN%");
        text = text.replace("ධ්‍රෙ", "fO%");
        text = text.replace("්‍ය", "H");
        text = text.replace("්‍ර", "%");
        text = text.replace("ෂෞ", "fI!");
        text = text.replace("ඡෞ", "fP!");
        text = text.replace("ශෞ", "fY!");
        text = text.replace("බෞ", "fn!");
        text = text.replace("චෞ", "fp!");
        text = text.replace("ඩෞ", "fv!");
        text = text.replace("ෆෞ", "f*!");
        text = text.replace("ගෞ", "f.!");
        text = text.replace("ජෞ", "fc!");
        text = text.replace("කෞ", "fl!");
        text = text.replace("ලෞ", "f,!");
        text = text.replace("මෞ", "fu!");
        text = text.replace("නෞ", "fk!");
        text = text.replace("පෞ", "fm!");
        text = text.replace("දෞ", "fo!");
        text = text.replace("රෞ", "fr!");
        text = text.replace("සෞ", "fi!");
        text = text.replace("ටෞ", "fg!");
        text = text.replace("තෞ", "f;!");
        text = text.replace("භෞ", "BUA");//
        text = text.replace("ඤෞ", "f[!");
        text = text.replace("ෂෝ", "fIda");
        text = text.replace("ඹෝ", "fUda");
        text = text.replace("ඡෝ", "fPda");
        text = text.replace("ඪෝ", "fVda");
        text = text.replace("ඝෝ", "f>da");
        text = text.replace("ඛෝ", "fLda");
        text = text.replace("ළෝ", "L\\");//
        text = text.replace("ඟෝ", "fÛda");
        text = text.replace("ණෝ", "fKda");
        text = text.replace("ඵෝ", "fMda");
        text = text.replace("ඨෝ", "fGda");
        text = text.replace("ඬෝ", "fËda");
        text = text.replace("ශෝ", "fYda");
        text = text.replace("ඥෝ", "f{da");
        text = text.replace("ඳෝ", "f|da");
        text = text.replace("ක්‍ෂෝ", "fÌda");
        text = text.replace("බෝ", "b\\");//
        text = text.replace("චෝ", "c\\");//
        text = text.replace("ඩෝ", "d\\");//
        text = text.replace("ෆෝ", "f\\");//
        text = text.replace("ගෝ", "g\\");//h
        text = text.replace("හෝ", "h\\");//
        text = text.replace("ජෝ", "j\\");//
        text = text.replace("කෝ", "k\\");//
        text = text.replace("ලෝ", "l\\");//
        text = text.replace("මෝ", "m\\");//
        text = text.replace("නෝ", "n\\");//
        text = text.replace("පෝ", "p\\");//
        text = text.replace("දෝ", "z\\");//
        text = text.replace("රෝ", "r\\");//
        text = text.replace("සෝ", "s\\");//
        text = text.replace("ටෝ", "t\\");//
        text = text.replace("වෝ", "v\\");//
        text = text.replace("තෝ", "t\\");//
        text = text.replace("භෝ", "B\\");//
        text = text.replace("යෝ", "y\\");//
        text = text.replace("ඤෝ", "f[da");
        text = text.replace("ධෝ", "fOda");
        text = text.replace("ථෝ", "f:da");
        text = text.replace("ෂො", "fId");
        text = text.replace("ඹො", "fUd");
        text = text.replace("ඡො", "fPd");
        text = text.replace("ඪො", "fVd");
        text = text.replace("ඝො", "f>d");
        text = text.replace("ඛො", "fLd");
        text = text.replace("ළො", "f<d");
        text = text.replace("ඟො", "fÕd");
        text = text.replace("ණො", "fKd");
        text = text.replace("ඵො", "fMd");
        text = text.replace("ඨො", "fGd");
        text = text.replace("ඬො", "fËd");
        text = text.replace("ශො", "fYd");
        text = text.replace("ඥො", "f{d");
        text = text.replace("ඳො", "f|d");
        text = text.replace("ක්‍ෂො", "fÌd");
        text = text.replace("බො", "bO");//
        text = text.replace("චො", "cO");//
        text = text.replace("ඩො", "dO");//
        text = text.replace("ෆො", "fO");//
        text = text.replace("ගො", "gO");//
        text = text.replace("හො", "hO");//
        text = text.replace("ජො", "jO");//
        text = text.replace("කො", "kO");//
        text = text.replace("ලො", "lO");//
        text = text.replace("මො", "mO");//
        text = text.replace("නො", "nO");//
        text = text.replace("පො", "pO");//
        text = text.replace("දො", "zO");//
        text = text.replace("රො", "rO");//
        text = text.replace("සො", "sO");//
        text = text.replace("ටො", "tO");//
        text = text.replace("වො", "vO");//
        text = text.replace("තො", "wO");//
        text = text.replace("භො", "BO");//
        text = text.replace("යො", "yO");//
        text = text.replace("ඤො", "f[d");
        text = text.replace("ධො", "fOd");
        text = text.replace("ථො", "f:d");
        text = text.replace("ෂේ", "fIa");
        text = text.replace("ඹේ", "fò");
        text = text.replace("ඡේ", "fþ");
        text = text.replace("ඝේ", "f>a");
        text = text.replace("ඛේ", "fÄ");
        text = text.replace("ළේ", "f<a");
        text = text.replace("ඟේ", "fÛa");
        text = text.replace("ණේ", "fKa");
        text = text.replace("ඵේ", "fMa");
        text = text.replace("ඨේ", "fGa");
        text = text.replace("ඬේ", "få");
        text = text.replace("ශේ", "fYa");
        text = text.replace("ඥේ", "f{a");
        text = text.replace("ඳේ", "f|a");
        text = text.replace("ක්‍ෂේ", "fÌa");
        text = text.replace("බේ", "<b");//
        text = text.replace("චේ", "<c");//
        text = text.replace("ඩේ", "<d");//
        text = text.replace("ෆේ", "<f");//
        text = text.replace("ගේ", "<g");//
        text = text.replace("හේ", "<h");//
        text = text.replace("පේ", "<p");//
        text = text.replace("කේ", "<k");//
        text = text.replace("ලේ", "<l");//
        text = text.replace("මේ", "<m");//
        text = text.replace("නේ", "<n");//
        text = text.replace("ජේ", "<j");//
        text = text.replace("දේ", "<z");//
        text = text.replace("රේ", "<r");//
        text = text.replace("සේ", "<s");//
        text = text.replace("ටේ", "<t");//
        text = text.replace("වේ", "<v");//
        text = text.replace("තේ", "<w");//
        text = text.replace("භේ", "fNa");
        text = text.replace("යේ", "<y");//
        text = text.replace("ඤේ", "f[a");
        text = text.replace("ධේ", "fè");
        text = text.replace("ථේ", "f:a");
        text = text.replace("ෂෙ", "fI");
        text = text.replace("ඹෙ", "fU");
        text = text.replace("ඓ", "ft");
        text = text.replace("ඡෙ", "fP");
        text = text.replace("ඪෙ", "fV");
        text = text.replace("ඝෙ", "f>");
        text = text.replace("ඛෙ", "fn");
        text = text.replace("ළෙ", "f<");
        text = text.replace("ඟෙ", "fÛ");
        text = text.replace("ණෙ", "fK");
        text = text.replace("ඵෙ", "fM");
        text = text.replace("ඨෙ", "fG");
        text = text.replace("ඬෙ", "fË");
        text = text.replace("ශෙ", "fY");
        text = text.replace("ඥෙ", "f{");
        text = text.replace("ඳෙ", "fË");
        text = text.replace("ක්‍ෂෙ", "fÌ");
        text = text.replace("බෙ", "fn");
        text = text.replace("චෙ", "fp");
        text = text.replace("ඩෙ", "fv");
        text = text.replace("ෆෙ", "f*");
        text = text.replace("ගෙ", "Eg");//
        text = text.replace("හෙ", "fy");
        text = text.replace("ජෙ", "fc");
        text = text.replace("කෙ", "Ek");//
        text = text.replace("ලෙ", "El");//
        text = text.replace("මෙ", "Em");//
        text = text.replace("නෙ", "En");//
        text = text.replace("පෙ", "Ep");//
        text = text.replace("දෙ", "Ez");//
        text = text.replace("රෙ", "Er");//
        text = text.replace("සෙ", "Es");//
        text = text.replace("ටෙ", "Et");//
        text = text.replace("වෙ", "Ev");//
        text = text.replace("තෙ", "Ew");//
        text = text.replace("භෙ", "EB");//
        text = text.replace("යෙ", "Ey");//
        text = text.replace("ඤෙ", "f[");
        text = text.replace("ධෙ", "fO");
        text = text.replace("ථෙ", "f:");
        text = text.replace("තු", "wU");//
        text = text.replace("ගු", "gU");//
        text = text.replace("කු", "kU");//
        text = text.replace("තූ", "w}");//
        text = text.replace("ගූ", "g}");//
        text = text.replace("කූ", "k}");//
        text = text.replace("රු", "rU");//
        text = text.replace("රූ", "r}");//
        text = text.replace("ආ", "aA");//
        text = text.replace("ඇ", "q");//
        text = text.replace("ඈ", "qQ");//
        text = text.replace("ඌ", "uU");//
        text = text.replace("ඖ", "aU");//
        text = text.replace("ඒ", "Ee");//
        text = text.replace("ඕ", "oO");//
        text = text.replace("ඳි", "XI");//
        text = text.replace("ඳී", "X{");//
        text = text.replace("දූ", "¥");
        text = text.replace("දී", "§");
        text = text.replace("ලූ", "¨");
        text = text.replace("ර්‍ය", "©");
        text = text.replace("ඳූ", "ª");
        text = text.replace("ර්", "r|");
        text = text.replace("ඨි", "À");
        text = text.replace("ඨී", "Á");
        text = text.replace("ඡී", "Â");
        text = text.replace("ඛ්", "Ä");
        text = text.replace("ඛි", "Å");
        text = text.replace("ලු", "lU");//
        text = text.replace("ඛී", "Ç");
        text = text.replace("දි", "È");
        text = text.replace("ච්", "É");
        text = text.replace("ජ්", "j|");
        text = text.replace("රී", "r{");//
        text = text.replace("ඪී", "Î");
        text = text.replace("ඪී", "Ð,");
        text = text.replace("චි", "Ñ");
        text = text.replace("ථී", "Ò");
        text = text.replace("ථී", "Ó");
        text = text.replace("ජී", "j");
        text = text.replace("චී", "Ö");
        text = text.replace("ඞ්", "Ù");
        text = text.replace("ඵී", "Ú");
        text = text.replace("ට්", "t|");//
        text = text.replace("ඵි", "Ý");
        text = text.replace("රි", "rI");//
        text = text.replace("ටී", "t{");//
        text = text.replace("ටි", "tI");//
        text = text.replace("ඩ්", "d|");//
        text = text.replace("ඩී", "d{");//
        text = text.replace("ඩි", "dI");//
        text = text.replace("ඬ්", "å");
        text = text.replace("ඬි", "ç");
        text = text.replace("ධ්", "è");
        text = text.replace("ඬී", "é");
        text = text.replace("ධි", "ê");
        text = text.replace("ධී", "ë");
        text = text.replace("බි", "ì");
        text = text.replace("බ්", "í");
        text = text.replace("බී", "î");
        text = text.replace("ම්", "m|");
        text = text.replace("ජි", "ð");
        text = text.replace("මි", "mI");//
        text = text.replace("ඹ්", "ò");
        text = text.replace("මී", "m{");//
        text = text.replace("ඹි", "B{");//
        text = text.replace("ව්", "v|");//
        text = text.replace("ඹී", "ö");
        text = text.replace("ඳු", "÷");
        text = text.replace("ද්‍ර", "ø");
        text = text.replace("වී", "v{");//
        text = text.replace("වි", "vI");//
        text = text.replace("ඞ්", "û");
        text = text.replace("ඞී", "ü");
        text = text.replace("ඡි", "ý");
        text = text.replace("ඡ්", "C|");//
        text = text.replace("දු", "zU");//
        text = text.replace("ජ්", "j|");//
        text = text.replace("ර්‍ණ", "“");
        text = text.replace("ණී", "”");
        text = text.replace("ජී", "„");
        text = text.replace("ඡි", "‰");
        text = text.replace("ඩි", "");
        text = text.replace("ඤු", "™");
        text = text.replace("ග", "g");//
        text = text.replace("ළු", "¿");
        text = text.replace("ෂ", "x");//
        text = text.replace("ං", "]");//
        text = text.replace("ඃ", "[");//
        text = text.replace("ඹ", "U");
        text = text.replace("ඡ", "P");
        text = text.replace("ඪ", "V");
        text = text.replace("ඝ", ">");
        text = text.replace("ඊ", "iI");
        text = text.replace("ඣ", "CO");
        text = text.replace("ඛ", "L");
        text = text.replace("ළ", "<");
        text = text.replace("ඟ", "Û");
        text = text.replace("ණ", "N");//
        text = text.replace("ඵ", "M");
        text = text.replace("ඨ", "G");
        text = text.replace("ඃ", "#");
        text = text.replace(":", "(");
        text = text.replace("-", ")");
        text = text.replace("ෆ", "f");//
        text = text.replace("ල", "l");//f
        text = text.replace("-", "-");
        text = text.replace("රැ", "/");
        text = text.replace("ථ", "W");//
        text = text.replace("ත", "w");//
        text = text.replace("ළ", "L");//
        text = text.replace("ඝ", ">");
        text = text.replace("රෑ", "?");
        text = text.replace("ඊ", "B");
        text = text.replace("ක‍", "C");
        text = text.replace("‍ෘ", "D");
        text = text.replace("ෑ", ">");
        text = text.replace("ත‍", "t");
        text = text.replace("ඨ", "G");
        text = text.replace("්‍ය", "H");
        text = text.replace("ෂ", "x");//
        text = text.replace("න‍", "n");//
        text = text.replace("ණ", "N");//
        text = text.replace("ඛ", "K");//
        text = text.replace("ඵ", "P");//
        text = text.replace("භ", "B");//
        text = text.replace("ධ", "O");
        text = text.replace("ඡ", "P");
        text = text.replace("ඍ", "R");
        text = text.replace("ඔ", "o");//
        text = text.replace("ඹ", "U");
        text = text.replace("ඪ", "y");//
        text = text.replace("උ", "u");//
        text = text.replace("ශ", "x");//
        text = text.replace("ඤ", "[");
        text = text.replace("ඉ", "i");//
        text = text.replace("ජ", "j");//i
        text = text.replace("ට", "t");//
        text = text.replace("ය", "y");//
        text = text.replace("ස", "s");//y
        text = text.replace("ව", "v");//
        text = text.replace("න", "n");//
        text = text.replace("ක", "k");//
        text = text.replace("ප", "p");//
        text = text.replace("බ", "b");//
        text = text.replace("ද", "z");//
        text = text.replace("ච", "c");//
        text = text.replace("ර", "r");//
        text = text.replace("එ", "e");//
        text = text.replace("ම", "m");//
        text = text.replace("ඩ", "d");//
        text = text.replace("අ", "a");//
        text = text.replace("හ", "h");//
        text = text.replace("ඥ", "{");
        text = text.replace("ඳ", "X");//a
        text = text.replace("ක්‍ෂ", "Ì");
        text = text.replace("ැ", "Q");//
        text = text.replace("ෑ", ">");//
        text = text.replace("ෙ", "f");
        text = text.replace("ු", "U");//
        text = text.replace("ි", "I");//
        text = text.replace("ූ", "UU");//
        text = text.replace("ී", "{");
        text = text.replace("ෘ", "D");
        text = text.replace("ෲ", "DD");
        text = text.replace("ෟ", "!");
        text = text.replace("ා", "AA");//
        text = text.replace("්", "|");//
        text = text.replace("￦", "\"");
        text = text.replace("�", "'");
        text = text.replace("￫", "^");
        text = text.replace("￩", "&");
        text = text.replace("ￔ", ")");
        text = text.replace("ￓ", "@");
        text = text.replace("ￒ", "`");
        text = text.replace("ￏ", "}");
        text = text.replace("ￎ", "~");
        text = text.replace("ඟු", "GU");//new add
        text = text.replace("ශ්\u200Dරී", "x{U");//new add
        Legacy.setText(text);
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

