package com.example.smartdoctor.utils;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = SmartDoctor.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface SmartDoctor_GeneratedInjector {
  void injectSmartDoctor(SmartDoctor smartDoctor);
}
