package com.example.weitlos.prv_prototype_one;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Rectangle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

/**
 * TODO: document your custom view class.
 */
public class RoomCard extends View {

    private int mRoomId;
    private String mName = "kleine chilenische Küche";
    private String mLocation = "Mannheim Neckarau";
    private float mPriceRepresentation = 5.0f;
    private Bitmap mPreview;

    private TextPaint mTextPaint;
    private RectF mRectCardBackground;

    private float mInfoSectionHeight = 200f;

    private float mPadding = 0f;
    private float mCornerRadius = 15f;

    private File picture;

    public RoomCard(Context context) {
        super(context);
        init(null, 0);
    }

    public RoomCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RoomCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        /*
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RoomCard, defStyle, 0);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.

        a.recycle();
        */

        mRectCardBackground = new RectF();
/*
        picture = null;
        try {
            picture = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child("https://firebasestorage.googleapis.com/v0/b/db-prvprototypeone.appspot.com/o/house.jpg?alt=media&token=08fa95a5-cca1-4135-82b7-5e80b6b4c4f4");

        riversRef.getFile(picture)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...

                        mPreview = BitmapFactory.decodeFile(picture.getPath());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });



        // Set up a default TextPaint object
        */

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();

    }

    private void invalidateTextPaintAndMeasurements() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;


        mRectCardBackground.set(0, 0, this.getWidth(), this.getHeight() - mInfoSectionHeight);

        String textColor = "#E6E6E6"; // Dark: #232930

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(Color.parseColor("#E6E6E6"));
        canvas.drawRoundRect(mRectCardBackground, mCornerRadius, mCornerRadius, p );

        // Location
        p.setTextSize(45);
        p.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "gadugi.ttf" ));
        p.setColor(Color.parseColor(textColor));
        canvas.drawText("in " + mLocation, 1, (this.getHeight() - (mInfoSectionHeight)) + (60 + 60 + 25), p);

        // Name
        p.setTextSize(65);
        p.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "gadugib.ttf" ));
        canvas.drawText(mName, 1, (this.getHeight() - (mInfoSectionHeight)) + (60 + 25), p);

        // Price
        p.setTextAlign(Paint.Align.RIGHT);
        p.setTextSize(65);
        p.setColor(Color.parseColor("#F9B031"));
        canvas.drawText(mPriceRepresentation + "€", this.getWidth() - 1, (this.getHeight() - (mInfoSectionHeight)) + (60 + 25), p);
    }

}
