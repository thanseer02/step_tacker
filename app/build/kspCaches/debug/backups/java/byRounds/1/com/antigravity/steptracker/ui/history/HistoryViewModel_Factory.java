package com.antigravity.steptracker.ui.history;

import com.antigravity.steptracker.domain.repository.StepRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<StepRepository> stepRepositoryProvider;

  public HistoryViewModel_Factory(Provider<StepRepository> stepRepositoryProvider) {
    this.stepRepositoryProvider = stepRepositoryProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(stepRepositoryProvider.get());
  }

  public static HistoryViewModel_Factory create(Provider<StepRepository> stepRepositoryProvider) {
    return new HistoryViewModel_Factory(stepRepositoryProvider);
  }

  public static HistoryViewModel newInstance(StepRepository stepRepository) {
    return new HistoryViewModel(stepRepository);
  }
}
