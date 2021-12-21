package lk.devildeveloper.sanoj.sakmadalaconvert;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.effect.EffectFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.androidadvance.topsnackbar.TSnackbar;
import com.divyanshu.colorseekbar.ColorSeekBar;
import com.pixplicity.easyprefs.library.Prefs;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnSelectedListener;
import ja.burhanrashid52.photoeditor.CustomEffect;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.SaveSettings;

public class Photo_Editor extends AppCompatActivity {
    private PhotoEditorView mPhotoEditorView;
    private PhotoEditor mPhotoEditor;
    private ImageButton mSave;
    private String mSavefileformat = ".jpg";
    private ACProgressFlower dialog;
    private ImageButton mRedu;
    private ImageButton mUndo;
    private ImageButton mEmoji;
    private RelativeLayout mRootview;
    private EditText mEmojiconEditText;
    private ImageButton mSend;
    private RelativeLayout mMidlbar;
    private RelativeLayout mImojoaria;
    private ImageButton mOpenimoji;
    private EditText emojIcon;
    private ImageButton mBrushs;
    private RelativeLayout mBrush;
    private ColorSeekBar mColorSeekBar;
    private TextView mTextcolor;
    private SeekBar mSeekBar;
    private ImageButton mErazer;
    private RelativeLayout mErazeraria;
    private SeekBar mSeekBarbrigh;
    private RelativeLayout mRightnes;
    private ImageButton mBright;
    private ImageButton mAddimage;
    private ImageButton mSakmadala;
    private EditText userInputDialogEditText;
    private TextView mSakmadaladia;
    private ColorSeekBar mColorSeekBardia;
    private int yTextcolor = Color.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);



        installid();
        runButton();


        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Saving image..")
                .fadeColor(Color.DKGRAY).build();


        mPhotoEditorView.getSource().setImageURI(Uri.parse(Prefs.getString("imagURL", "")));
        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .setClipSourceImage(true)
                .build();


    }
    void installid(){
        mPhotoEditorView = findViewById(R.id.photoEditorView);
        mSave = findViewById(R.id.save);
        mRedu = findViewById(R.id.redu);
        mUndo = findViewById(R.id.undo);
        mEmoji = findViewById(R.id.emoji);
        mRootview = findViewById(R.id.rootview);
        mEmojiconEditText = findViewById(R.id.emojicon_edit_text);
        mSend = findViewById(R.id.send);
        mMidlbar = findViewById(R.id.midlbar);
        mImojoaria = findViewById(R.id.imojoaria);
        mOpenimoji = findViewById(R.id.openimoji);
        mBrush = findViewById(R.id.brush);
        mBrushs = findViewById(R.id.brushs);
        mColorSeekBar = findViewById(R.id.color_seek_bar);
        mTextcolor = findViewById(R.id.textcolor);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setMax(100);
        mSeekBar.setProgress((int) 22);
        mSeekBar.setOnSeekBarChangeListener(new Photo_Editor.mylestner());
        mErazer = findViewById(R.id.erazer);
        mErazeraria = findViewById(R.id.erazeraria);
        mSeekBarbrigh = findViewById(R.id.seekBarbrigh);
        mSeekBarbrigh.setMax((int) 100);
        mSeekBarbrigh.setProgress((int) 10);
        mSeekBarbrigh.setOnSeekBarChangeListener(new Photo_Editor.darklistner());
        mRightnes = findViewById(R.id.rightnes);
        mBright = findViewById(R.id.bright);
        mAddimage = findViewById(R.id.addimage);
        mSakmadala = findViewById(R.id.sakmadala);
    }

    private class darklistner implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            CustomEffect customEffect = new CustomEffect.Builder(EffectFactory.EFFECT_BRIGHTNESS)
                    .setParameter("brightness", 9.0f/progress)
                    .build();
            mPhotoEditor.setFilterEffect(customEffect);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            showsnak("❤️  Changed Darkness " + seekBar.getProgress()+"X");
        }
    }

    private class mylestner implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mPhotoEditor.setBrushSize(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            showsnak("❤️  Changed Brush Size " + seekBar.getProgress()+"X");
        }
    }
    void runButton(){
        mColorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i) {
                mPhotoEditor.setBrushColor(i);
                mTextcolor.setTextColor(i);
                mPhotoEditor.setBrushDrawingMode(true);
            }
        });

        mSave.setOnClickListener(view ->{
            saveimage();
        });
        mAddimage.setOnClickListener(view ->{
            TedImagePicker.with(this)
                    .start(new OnSelectedListener() {
                        @Override
                        public void onSelected(@NotNull Uri uri) {

                            try {
                                Bitmap bmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                                mPhotoEditor.addImage(bmp);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                              ;}




              }});
        });
        mSakmadala.setOnClickListener(view ->{
            Addtext();
        });
        mErazer.setOnClickListener(view ->{
            mPhotoEditor.brushEraser();
        });

        mUndo.setOnClickListener(view ->{
            mPhotoEditor.undo();
            showsnak("❤️  Undo Last Change");
        });

        mBright.setOnClickListener(view ->{
            if (mMidlbar.getVisibility()==View.GONE||mRightnes.getVisibility()==View.GONE){
                mRightnes.setVisibility(View.VISIBLE);
                mMidlbar.setVisibility(View.VISIBLE);
                mPhotoEditor.setBrushDrawingMode(false);
                mImojoaria.setVisibility(View.GONE);
                mErazeraria.setVisibility(View.GONE);
                mBrush.setVisibility(View.GONE);
            }else{
                mMidlbar.setVisibility(View.GONE);
                mRightnes.setVisibility(View.GONE);
                mPhotoEditor.setBrushDrawingMode(false);
                mImojoaria.setVisibility(View.GONE);
                mBrush.setVisibility(View.GONE);
            }
        });
        mRedu.setOnClickListener(view ->{
            mPhotoEditor.redo();
            showsnak("❤️  Redo Last Change");
        });
        mBrushs.setOnClickListener(view ->{
            if (mMidlbar.getVisibility() == View.GONE||mBrush.getVisibility() ==View.GONE){
                mMidlbar.setVisibility(View.VISIBLE);
                mErazeraria.setVisibility(View.VISIBLE);
                mPhotoEditor.setBrushDrawingMode(true);
                mRightnes.setVisibility(View.GONE);
                showsnak("❤️  Bursh activated");
                mBrush.setVisibility(View.VISIBLE);
                mImojoaria.setVisibility(View.GONE);
            }else{
                mPhotoEditor.setBrushDrawingMode(false);
                mErazeraria.setVisibility(View.GONE);
                mRightnes.setVisibility(View.GONE);
                showsnak("❤️  Bursh Deactivated");
                mMidlbar.setVisibility(View.GONE);
                mBrush.setVisibility(View.GONE);
            }
        });
        mSend.setOnClickListener(view ->{
            mPhotoEditor.addEmoji(mEmojiconEditText.getText().toString());
            mMidlbar.setVisibility(View.GONE);
            mImojoaria.setVisibility(View.GONE);
            mEmojiconEditText.setText("");
        });
        mEmoji.setOnClickListener(view ->{
            if (mMidlbar.getVisibility() == View.GONE||mImojoaria.getVisibility() ==View.GONE){
                mMidlbar.setVisibility(View.VISIBLE);
                mPhotoEditor.setBrushDrawingMode(false);
                mRightnes.setVisibility(View.GONE);
                mImojoaria.setVisibility(View.VISIBLE);
                mErazeraria.setVisibility(View.GONE);
                mBrush.setVisibility(View.GONE);
            }else{
                mMidlbar.setVisibility(View.GONE);
                mErazeraria.setVisibility(View.GONE);
                mRightnes.setVisibility(View.GONE);
                mImojoaria.setVisibility(View.GONE);
            }
        });

    }
    @SuppressLint("MissingPermission")
    public void saveimage() {
        dialog.show();
        SaveSettings saveSettings = new SaveSettings.Builder()
                .setClearViewsEnabled(true)
                .setTransparencyEnabled(true)
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .setCompressQuality(100)
                .build();
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String savpath = path+"/IMG_"+System.currentTimeMillis()+mSavefileformat;
        mPhotoEditor.saveAsFile(savpath,saveSettings, new PhotoEditor.OnSaveListener() {
            @Override
            public void onSuccess(@NonNull String imagePath) {
                dialog.dismiss();
                Log.e("PhotoEditor", "Image Saved Successfully");
                showsnak("❤️  Image Saved Successfully"+"\n"+imagePath);

            }

            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("PhotoEditor", "Failed to save Image");
                showsnak("❤️ Failed to save Image"+"\n"+exception);
                dialog.dismiss();
            }
        });
    }
    public void showsnak(String msg){
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), msg, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#191919"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
    public void Addtext(){
        yTextcolor = Color.WHITE;
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(Photo_Editor.this);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Photo_Editor.this);
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        mSakmadaladia = (TextView) mView.findViewById(R.id.sakmadala);
        mSakmadaladia.setTextColor(yTextcolor);
        mColorSeekBardia = (ColorSeekBar) mView.findViewById(R.id.color_seek_bar);
        mColorSeekBardia.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i) {
                mSakmadaladia.setTextColor(i);
                yTextcolor = i;
            }
        });
        userInputDialogEditText.addTextChangedListener(new TextWatcher() {
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


        final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialogAndroid.show();

        Button mAddtext = (Button) mView.findViewById(R.id.addtext);
        mAddtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface mTextRobotoTf = ResourcesCompat.getFont(Photo_Editor.this, R.font.sak);
                mPhotoEditor.addText(mTextRobotoTf,mSakmadaladia.getText().toString(), yTextcolor);
                alertDialogAndroid.dismiss();
            }
        });
    }
    public void unicodechanger(){
        String text = userInputDialogEditText.getText().toString();
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
        mSakmadaladia.setText(text);
        mSakmadaladia.setTypeface(mSakmadaladia.getTypeface(), Typeface.BOLD);
    }




}