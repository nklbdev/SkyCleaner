package com.polly5315.skycleaner;

import com.badlogic.gdx.math.Vector2;

public interface IPlaceable {
    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    void setPosition(float x, float y);
    void setPosition(Vector2 position);
}
