package com.hisham.hishambasicandroidsamples.longwalks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.EditText;

import com.hisham.hishambasicandroidsamples.R;

public class LongWalksActivity extends AppCompatActivity {


    EditText myEditText;
    String startText = "I'm The First Part";
    String lastText = "I'm The Last Part";

    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_walks);

        final SpannableStringBuilder firstStringBuilder = new SpannableStringBuilder(startText);
        final SpannableStringBuilder lastStringBuilder = new SpannableStringBuilder(lastText);

        StyleSpan firstStyleSpan = new StyleSpan(android.graphics.Typeface.BOLD);

        firstStringBuilder.setSpan(firstStyleSpan, 0, firstStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
        lastStringBuilder.setSpan(firstStyleSpan, 0, lastStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold

        myEditText = findViewById(R.id.myEditText);


        spannableStringBuilder.append(firstStringBuilder);
        spannableStringBuilder.append("____________");
        spannableStringBuilder.append(lastStringBuilder);

        myEditText.setText(spannableStringBuilder);
        Selection.setSelection(myEditText.getText(), startText.length() + 1);

        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().startsWith(firstStringBuilder.toString())
                        || !s.toString().contains(lastText)) {
                    Log.e("StringBuilder_TAG", spannableStringBuilder.toString());

                    myEditText.setText(spannableStringBuilder);
                    Selection.setSelection(myEditText.getText(), myEditText.getText().length() - lastStringBuilder.length() - 1);
                } else {

                    spannableStringBuilder.clear();
                    spannableStringBuilder.append(s.toString());
                    Log.e("My_TAG", spannableStringBuilder.toString());

                }

            }
        });

    }
}
