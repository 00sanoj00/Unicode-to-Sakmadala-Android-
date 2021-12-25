package lk.devildeveloper.sanoj.sakmadalaconvert;


import static android.graphics.Typeface.BOLD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
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
import lk.sakmadala.sakmadala;

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
            mPhotoEditor.setBrushDrawingMode(false);
            mImojoaria.setVisibility(View.GONE);
            mBrush.setVisibility(View.GONE);
            mMidlbar.setVisibility(View.GONE);
            mErazeraria.setVisibility(View.GONE);
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
                    sakcode();
            }
        });


        final AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialogAndroid.show();

        Button mAddtext = (Button) mView.findViewById(R.id.addtext);
        mAddtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSakmadala.buildDrawingCache();
                Typeface mTextRobotoTf = ResourcesCompat.getFont(Photo_Editor.this, R.font.sak);
                mPhotoEditor.addText(mTextRobotoTf,mSakmadaladia.getText().toString(), yTextcolor);
                alertDialogAndroid.dismiss();
            }
        });
    }
    public void sakcode(){
        String output = sakmadala.sakmadalatext(userInputDialogEditText.getText().toString());
        mSakmadaladia.setText(output);
        mSakmadaladia.setTypeface(mSakmadaladia.getTypeface(), BOLD);
    }

    public void onTextCreate(String text) {
        Bitmap mTexture = BitmapFactory.decodeResource(getResources(),
                R.drawable.shader_background);


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(200);
        paint.setARGB(255, 0, 0, 0);
        paint.setSubpixelText(true);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setShadowLayer(3f, 3f, 3f, Color.WHITE);
        Typeface typeface = Typeface.create(Typeface.createFromAsset(Photo_Editor.this.getAssets(), "fonts/sak.otf"), Typeface.BOLD);
        paint.setTypeface(typeface);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        canvas.drawText(text, 0, 220, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawBitmap(mTexture, 0, 0, paint);
        paint.setXfermode(null);

        mPhotoEditor.addImage(result);
    }

}