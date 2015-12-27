package com.polly5315.skycleaner;

public interface IShip extends IPlaceable, IEntity {
    void fire();
    void setDesiredPosition(float desiredPosition);
    void setWeapon(IWeapon weapon);
    void setForwardSpeed(float forwardSpeed);
    void setSideSpeed(float sideSpeed);
}
