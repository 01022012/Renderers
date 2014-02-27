package com.pedrogomez.renderers.ui.renderers;

import android.content.Context;
import com.pedrogomez.renderers.R;
import com.pedrogomez.renderers.model.Video;
import com.squareup.picasso.Picasso;


/**
 * Favorite video renderer created to implement the presentation logic for videos.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class FavoriteVideoRenderer extends VideoRenderer {

    /*
     * Constructor
     */

    public FavoriteVideoRenderer(Context context) {
        super(context);
    }

    /*
     * Implemented methods
     */


    @Override
    protected void renderLabel() {
        getLabel().setText("FAVORITE");
    }

    @Override
    protected void renderMarker(Video video) {
        Picasso.with(getContext()).load(R.drawable.fav_active).into(getMarker());
    }

}
