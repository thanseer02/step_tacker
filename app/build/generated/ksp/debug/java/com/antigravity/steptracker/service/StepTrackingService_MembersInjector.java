package com.antigravity.steptracker.service;

import com.antigravity.steptracker.data.local.datastore.AppSettings;
import com.antigravity.steptracker.domain.repository.StepRepository;
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
public final class StepTrackingService_MembersInjector implements MembersInjector<StepTrackingService> {
  private final Provider<StepRepository> stepRepositoryProvider;

  private final Provider<AppSettings> appSettingsProvider;

  public StepTrackingService_MembersInjector(Provider<StepRepository> stepRepositoryProvider,
      Provider<AppSettings> appSettingsProvider) {
    this.stepRepositoryProvider = stepRepositoryProvider;
    this.appSettingsProvider = appSettingsProvider;
  }

  public static MembersInjector<StepTrackingService> create(
      Provider<StepRepository> stepRepositoryProvider, Provider<AppSettings> appSettingsProvider) {
    return new StepTrackingService_MembersInjector(stepRepositoryProvider, appSettingsProvider);
  }

  @Override
  public void injectMembers(StepTrackingService instance) {
    injectStepRepository(instance, stepRepositoryProvider.get());
    injectAppSettings(instance, appSettingsProvider.get());
  }

  @InjectedFieldSignature("com.antigravity.steptracker.service.StepTrackingService.stepRepository")
  public static void injectStepRepository(StepTrackingService instance,
      StepRepository stepRepository) {
    instance.stepRepository = stepRepository;
  }

  @InjectedFieldSignature("com.antigravity.steptracker.service.StepTrackingService.appSettings")
  public static void injectAppSettings(StepTrackingService instance, AppSettings appSettings) {
    instance.appSettings = appSettings;
  }
}
