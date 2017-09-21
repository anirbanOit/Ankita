package com.oit.test.api;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.widget.Button;

/**
 * Created by OPTLPTP061 on 05-09-2017.
 */

public class GenderRadio {
    boolean setFemaleFlag = false;
    boolean setMaleFlag = false;
    Button textViewFemale;
    Button textViewMale;

    GenderRadio(Button textViewFemale, Button textViewMale) {
        this.textViewMale = textViewMale;
        this.textViewFemale = textViewFemale;
    }


    public boolean SettingGenderFlag(boolean setFemaleFlag, boolean setMaleFlag) {
        if (setFemaleFlag == true && setMaleFlag == false) {
            this.setFemaleFlag = setFemaleFlag;
            this.setMaleFlag = setMaleFlag;
            settingcolor();
        }
        if (setFemaleFlag == false && setMaleFlag == true) {
            this.setFemaleFlag = setFemaleFlag;
            this.setMaleFlag = setMaleFlag;
            settingcolor();
        }
        return false;
    }

    public void settingcolor() {
        if (setFemaleFlag == true && setMaleFlag == false) {
            textViewFemale.setBackgroundResource(R.drawable.selected);

            ShapeDrawable shapedrawable = new ShapeDrawable();
            shapedrawable.setShape(new RectShape());
            shapedrawable.getPaint().setColor(Color.BLACK);
            shapedrawable.getPaint().setStrokeWidth(10f);
            shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
            textViewMale.setBackgroundDrawable(shapedrawable);
        }
        if (setFemaleFlag == false && setMaleFlag == true) {
            textViewMale.setBackgroundResource(R.drawable.selected);

            ShapeDrawable shapedrawable = new ShapeDrawable();
            shapedrawable.setShape(new RectShape());
            shapedrawable.getPaint().setColor(Color.BLACK);
            shapedrawable.getPaint().setStrokeWidth(10f);
            shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
            textViewFemale.setBackgroundDrawable(shapedrawable);
        }
    }
}