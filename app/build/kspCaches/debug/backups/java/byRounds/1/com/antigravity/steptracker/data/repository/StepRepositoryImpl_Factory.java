package com.antigravity.steptracker.data.repository;

import com.antigravity.steptracker.data.local.db.dao.StepDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class StepRepositoryImpl_Factory implements Factory<StepRepositoryImpl> {
  private final Provider<StepDao> stepDaoProvider;

  public StepRepositoryImpl_Factory(Provider<StepDao> stepDaoProvider) {
    this.stepDaoProvider = stepDaoProvider;
  }

  @Override
  public StepRepositoryImpl get() {
    return newInstance(stepDaoProvider.get());
  }

  public static StepRepositoryImpl_Factory create(Provider<StepDao> stepDaoProvider) {
    return new StepRepositoryImpl_Factory(stepDaoProvider);
  }

  public static StepRepositoryImpl newInstance(StepDao stepDao) {
    return new StepRepositoryImpl(stepDao);
  }
}
