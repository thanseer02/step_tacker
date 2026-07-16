package com.antigravity.steptracker.di;

import com.antigravity.steptracker.data.local.db.AppDatabase;
import com.antigravity.steptracker.data.local.db.dao.StepDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideStepDaoFactory implements Factory<StepDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideStepDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StepDao get() {
    return provideStepDao(databaseProvider.get());
  }

  public static AppModule_ProvideStepDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideStepDaoFactory(databaseProvider);
  }

  public static StepDao provideStepDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStepDao(database));
  }
}
