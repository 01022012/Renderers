package com.pedrogomez.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.pedrogomez.renderers.exception.NullRendererBuiltException;

import java.util.List;

/**
 * Base adapter created to work RendererBuilders and Renderers. Other adapters have to use this one to create new lists.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class RendererAdapter<T> extends BaseAdapter {

    /*
     * Attributes
     */

    private LayoutInflater layoutInflater;
    private RendererBuilder<T> rendererBuilder;
    private List<T> collection;


    /*
     * Constructor
     */

    public RendererAdapter(LayoutInflater layoutInflater, RendererBuilder rendererBuilder, List<T> collection) {
        this.layoutInflater = layoutInflater;
        this.rendererBuilder = rendererBuilder;
        this.collection = collection;
    }

    /*
     * Implemented methods
     */

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public T getItem(int position) {
        return collection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T content = getItem(position);
        rendererBuilder.withContent(content);
        rendererBuilder.withConvertView(convertView);
        rendererBuilder.withParent(parent);
        rendererBuilder.withLayoutInflater(layoutInflater);
        Renderer<T> renderer = rendererBuilder.build();
        if (renderer == null) {
            throw new NullRendererBuiltException();
        }
        updateRendererExtraValues(renderer, position);
        renderer.render();
        return renderer.getRootView();
    }

    /**
     * Allows the client code to access the adaptee collection from subtypes of RendererAdapter.
     *
     * @return collection used in the adapter as the adaptee class.
     */
    protected List<T> getCollection() {
        return collection;
    }

    /**
     * Empty implementation created to allow the client code to extend this class without override getView method.
     * This method is called before render the renderer.
     *
     * @param renderer
     * @param position to be rendererd.
     */
    protected void updateRendererExtraValues(Renderer<T> renderer, int position) {
        //Empty
    }

    /*
     * Recycle methods
     */

    @Override
    public int getItemViewType(int position) {
        T content = getItem(position);
        return rendererBuilder.getItemViewType(content);
    }

    @Override
    public int getViewTypeCount() {
        return rendererBuilder.getViewTypeCount();
    }
}
