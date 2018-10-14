package com.hisham.passwordet;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {
    private Drawable passwordImageDrawable;
    private boolean isShowingPasswordContent = false;

    public PasswordEditText(Context context) {
        super(context);
        init(null, 0);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        passwordImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_password_show_black_24dp, null);
        this.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // hides the password
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float clearButtonStart; // Used for LTR languages
                    float clearButtonEnd;  // Used for RTL languages
                    boolean isPasswordTouched = false;
                    // Detect the touch in RTL or LTR layout direction.
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        // If RTL, get the end of the button on the left side.
                        clearButtonEnd = passwordImageDrawable
                                .getIntrinsicWidth() + getPaddingStart();
                        // If the touch occurred before the end of the button,
                        // set isPasswordTouched to true.
                        if (event.getX() < clearButtonEnd) {
                            isPasswordTouched = true;
                        }
                    } else {
                        // Layout is LTR.
                        // Get the start of the button on the right side.
                        clearButtonStart = (getWidth() - getPaddingEnd()
                                - passwordImageDrawable.getIntrinsicWidth());
                        // If the touch occurred after the start of the button,
                        // set isPasswordTouched to true.
                        if (event.getX() > clearButtonStart) {
                            isPasswordTouched = true;
                        }
                    }


                    // Check for actions if the button is tapped.
                    if (isPasswordTouched) {
                        // Check for ACTION_UP.
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            // Switch to the opaque version of clear button.
                            if (isShowingPasswordContent) {
                                setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                passwordImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_password_show_black_24dp, null);
                            } else {
                                passwordImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.password_rotated_180, null);
                                setTransformationMethod(PasswordTransformationMethod.getInstance());
                            }
                            isShowingPasswordContent = !isShowingPasswordContent;
                            showPasswordDrawable();
                            if (getText() != null)
                                setSelection(getText().length());
                            return true;
                        }
                    } else {
                        return false;
                    }

                }
                return false;
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    hidePasswordDrawable();
                } else {
                    showPasswordDrawable();
                }
            }
        });
    }

    private void showPasswordDrawable() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, passwordImageDrawable, null);
    }

    private void hidePasswordDrawable() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
