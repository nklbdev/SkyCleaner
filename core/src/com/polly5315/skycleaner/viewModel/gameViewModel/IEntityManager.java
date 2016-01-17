package com.polly5315.skycleaner.viewModel.gameViewModel;

import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IEntity;

import java.util.Collection;

public interface IEntityManager<TEntity extends IEntity> extends IDestructibleManagerViewModel<TEntity>, IUpdateable {
    void addEntity(TEntity entity);
    Collection<TEntity> getItems();
}
