package com.antigravity.steptracker;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class StepTrackerApplication_MembersInjector implements MembersInjector<StepTrackerApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public StepTrackerApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<StepTrackerApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new StepTrackerApplication_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(StepTrackerApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.antigravity.steptracker.StepTrackerApplication.workerFactory")
  public static void injectWorkerFactory(StepTrackerApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
