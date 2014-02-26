package com.pedrogomez.renderers.renderers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pedrogomez.renderers.R;
import com.pedrogomez.renderers.Video;

import java.util.Date;

/**
 * VideoRenderer created to contains the live video presentation logic.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class LiveVideoRenderer extends VideoRenderer {

    /*
     * Widgets
     */
    private TextView date;

    /*
     * Constructor
     */

    public LiveVideoRenderer(Context context) {
        super(context);
    }

    /*
     * Override methods
     */

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.live_video_renderer, parent, false);
    }

    @Override
    protected void setupView(View rootView) {
        super.setupView(rootView);
        date = (TextView) rootView.findViewById(R.id.date);
    }

    @Override
    protected void renderLabel() {
        label.setText("LIVE");
    }

    @Override
    protected void renderMarker(Video video) {
        marker.setVisibility(View.GONE);
    }

    @Override
    public void render() {
        super.render();
        Video video = getContent();
        renderDate(video);
    }

    /*
     * Auxiliary methods
     */

    private void renderDate(Video video) {
        String now = new Date().toLocaleString();
        date.setText(now);
    }

}

