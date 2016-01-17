package com.polly5315.skycleaner.views.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBlastViewModel;

public class BlastViewFactory implements IViewFactory<IBlastViewModel> {
    private final Animation _animation;

    public BlastViewFactory(Animation animation) {
        if (animation == null)
            throw new IllegalArgumentException("animation cannot be null");
        _animation = animation;
    }

    @Override
    public Actor create(IBlastViewModel viewModel) {
        return new BlastView(viewModel, _animation);
    }
}
