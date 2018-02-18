package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private static TextView mOrigin_tv = null;
    private static TextView mAlso_known_tv = null;
    private static TextView mIngredients_tv = null;
    private static TextView mDescription_tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mOrigin_tv = (TextView) findViewById(R.id.origin_tv);
        mAlso_known_tv = (TextView) findViewById(R.id.also_known_tv);
        mIngredients_tv = (TextView) findViewById(R.id.ingredients_tv);
        mDescription_tv = (TextView) findViewById(R.id.description_tv);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mDescription_tv.setText(sandwich.getDescription());

        /* set the Origin to N/A (not available) if the PlaceOfOrigin is null */
        mOrigin_tv.setText(sandwich.getPlaceOfOrigin().isEmpty() ? getString(R.string.not_available) : sandwich.getPlaceOfOrigin());

        /*
            The sandwich.getAlsoKnownAs() returns a list of Names,
            if the list has more than one item an newline is added
            to every item after the 1st item
         */
        boolean addNewLine = false;
        if (sandwich.getAlsoKnownAs().size() > 0) {
            for (String alsoKnow : sandwich.getAlsoKnownAs()) {
                if (addNewLine) {
                    /* Append a new line */
                    mAlso_known_tv.append(getString(R.string.new_line));
                } else {
                    addNewLine = true;
                }
                mAlso_known_tv.append(alsoKnow);
            }
        } else {
            // set the Also Know to N/A (not available) if there is not other knowing names
            mAlso_known_tv.append(getString(R.string.not_available));
        }

        /*
            The sandwich.getIngredients() returns a list of Ingredients,
            if the list has more than one item an newline is added
            to every item after the 1st item
         */
        addNewLine = false;
        for (String alsoKnow: sandwich.getIngredients()) {
            if (addNewLine) {
                /* Append a new line */
                mIngredients_tv.append(getString(R.string.new_line));
            } else
            {
                addNewLine = true;
            }
            mIngredients_tv.append(alsoKnow);
        }

    }
}
