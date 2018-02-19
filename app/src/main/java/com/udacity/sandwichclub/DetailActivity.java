package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private static TextView mOriginTextView = null;
    private static TextView mAlsoKnownTextView = null;
    private static TextView mIngredientsTextView = null;
    private static TextView mDescriptionTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mOriginTextView = (TextView) findViewById(R.id.origin_tv);
        mAlsoKnownTextView = (TextView) findViewById(R.id.also_known_tv);
        mIngredientsTextView = (TextView) findViewById(R.id.ingredients_tv);
        mDescriptionTextView = (TextView) findViewById(R.id.description_tv);

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

    /**
     * A method we wrote for convenience. This method will take a sandwich object as input
     * and use that object's data to populates the UI.
     * @param sandwich The Sandwich data
     */
    private void populateUI(Sandwich sandwich) {
        mDescriptionTextView.setText(sandwich.getDescription());

        /* set the Origin to N/A (not available) if the PlaceOfOrigin is empty */
        mOriginTextView.setText(sandwich.getPlaceOfOrigin().isEmpty() ? getString(R.string.not_available) : sandwich.getPlaceOfOrigin());

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
                    mAlsoKnownTextView.append(getString(R.string.new_line));
                } else {
                    addNewLine = true;
                }
                mAlsoKnownTextView.append(alsoKnow);
            }
        } else {
            // set the Also Know to N/A (not available) if there is not other knowing names
            mAlsoKnownTextView.append(getString(R.string.not_available));
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
                mIngredientsTextView.append(getString(R.string.new_line));
            } else
            {
                addNewLine = true;
            }
            mIngredientsTextView.append(alsoKnow);
        }

    }
}
