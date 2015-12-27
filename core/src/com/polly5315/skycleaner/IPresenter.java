package com.polly5315.skycleaner;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface IPresenter extends IUpdateable, IDestructible {
    void draw(Batch batch);
}
