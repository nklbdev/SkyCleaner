package com.polly5315.skycleaner.viewModel.gameViewModel.entities;

import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructible;
import com.polly5315.skycleaner.viewModel.gameViewModel.IDestructibleViewModel;
import com.polly5315.skycleaner.viewModel.gameViewModel.IUpdateable;

public interface IEntity extends IUpdateable, IDestructible, IDestructibleViewModel {
}
