package com.algaworks.algasensors.device.management.common;

import io.hypersistence.tsid.TSID;

import java.util.Optional;

public class IdGenerator {

  private static final TSID.Factory TSID_FACTORY;

  static {

    Optional.ofNullable(System.getenv("tsid.node"))
        .ifPresent(tsidNode -> System.setProperty("tsid.node", tsidNode));

    Optional.ofNullable(System.getenv("tsid.node.count"))
        .ifPresent(tsidNodeCount -> System.setProperty("tsid.node.count", tsidNodeCount));

    TSID_FACTORY = TSID.Factory.builder().build();
  }

  @SuppressWarnings({"squid:S1118", "squid:S1135"})
  public IdGenerator() {
    // TODO document why this constructor is empty
  }

  public static TSID generateTSID() {
    return TSID_FACTORY.generate();
  }
}
