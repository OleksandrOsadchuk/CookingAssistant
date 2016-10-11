package com.oleksandr.cookingassistant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oleksandr on 16-10-09.
 */
public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private List<Recipe> recipeList;

    public RecipeAdapter(Context context, int resource, List<Recipe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.recipeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recipe, parent, false);

        //Display title and details in the TextView widget
        Recipe recipe = recipeList.get(position);
        TextView tv1 = (TextView) view.findViewById(R.id.textView1);
        tv1.setText(recipe.getTitle());

        if(recipe.getDescription()!=null && recipe.getLink()!=null) {
            TextView tv2 = (TextView) view.findViewById(R.id.textView2);
            tv2.setText(recipe.getDescription() + "\n" + recipe.getLink());
        }

        //Display photo bitmap in ImageView widget
        if (recipe.getBitmap() != null) {
            ImageView image = (ImageView) view.findViewById(R.id.imageView1);
            image.setImageBitmap(recipe.getBitmap());
        }

        return view;
    }
}
