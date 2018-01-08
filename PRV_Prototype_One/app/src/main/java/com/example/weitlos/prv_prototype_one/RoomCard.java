package com.example.weitlos.prv_prototype_one;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
    private String mName;
    private String mLocation;
    private float mPriceRepresentation;
    private Bitmap mPreview;

    private TextPaint mTextPaint;
    private RectF mRectCardBackground;
    private RectF mRectInfoArea;

    private float mPadding = 0;
    private float mCornerRadius = 15;

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
        mRectInfoArea = new RectF();

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


        mRectCardBackground.set(mPadding, mPadding, this.getWidth() - mPadding, this.getHeight() - mPadding);
        mRectInfoArea.set(mRectCardBackground.left, mRectCardBackground.bottom - 200, mRectCardBackground.right, mRectCardBackground.bottom);

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(Color.parseColor("#EEEEEE"));
        canvas.drawRoundRect(mRectCardBackground, mCornerRadius, mCornerRadius, p );

        p.setColor(Color.parseColor("#666666"));
        canvas.drawRoundRect(mRectInfoArea, mCornerRadius, mCornerRadius, p);
        canvas.drawRect(mRectInfoArea.left, mRectInfoArea.top, mRectInfoArea.right, mRectInfoArea.top + mCornerRadius, p);

        //mPreview = BitmapFactory.decodeResource(getResources(), R.drawable.ic_house);
        if (mPreview != null)
            canvas.drawBitmap(mPreview, 0, 0, null);
    }

}
