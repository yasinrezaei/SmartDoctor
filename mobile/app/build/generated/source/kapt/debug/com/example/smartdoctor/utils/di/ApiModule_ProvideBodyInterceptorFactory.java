// Generated by Dagger (https://dagger.dev).
package com.example.smartdoctor.utils.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import okhttp3.logging.HttpLoggingInterceptor;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiModule_ProvideBodyInterceptorFactory implements Factory<HttpLoggingInterceptor> {
  @Override
  public HttpLoggingInterceptor get() {
    return provideBodyInterceptor();
  }

  public static ApiModule_ProvideBodyInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpLoggingInterceptor provideBodyInterceptor() {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideBodyInterceptor());
  }

  private static final class InstanceHolder {
    private static final ApiModule_ProvideBodyInterceptorFactory INSTANCE = new ApiModule_ProvideBodyInterceptorFactory();
  }
}
