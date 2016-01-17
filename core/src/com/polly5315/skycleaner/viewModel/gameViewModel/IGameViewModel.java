package com.polly5315.skycleaner.viewModel.gameViewModel;

import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBlastViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.entities.IBulletViewModel;

public interface IGameViewModel extends IUpdateable {
    IShipViewModel getShip();
    IDestructibleManagerViewModel<? extends IBulletViewModel> getBulletManagerViewModel();
    IDestructibleManagerViewModel<? extends IBlastViewModel> getBlastManagerViewModel();
    IDestructibleManagerViewModel<? extends IEnemyViewModel> getEnemyManagerViewModel();
}
