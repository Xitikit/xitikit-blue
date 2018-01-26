package org.xitikit.blue.b2c.kit.v2dot0.authentication;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class TimeComparison{

  private final Integer comparison;

  private TimeComparison(
    @Nullable final ZonedDateTime left,
    @Nullable final ZonedDateTime right){

    comparison = left == null || right == null ?
                   null :
                   left.compareTo(right);

  }

  public static TimeComparison comparisonOf(
    @Nullable final ZonedDateTime left,
    @Nullable final Long right){

    return new TimeComparison(
      left,
      convertLong(right)
    );
  }

  private static ZonedDateTime convertLong(final Long epochMilli){

    return epochMilli == null ? null :
             ZonedDateTime.ofInstant(
               Instant.ofEpochMilli(epochMilli),
               ZoneId.systemDefault()
             );
  }

  public static TimeComparison comparisonOf(
    @Nullable final Long left,
    @Nullable final ZonedDateTime right){

    return new TimeComparison(
      convertLong(left),
      right
    );
  }

  public static TimeComparison comparisonOf(
    @Nullable final Long left,
    @Nullable final Long right){

    return new TimeComparison(
      convertLong(left),
      convertLong(right)
    );
  }

  public boolean isGreater(){

    return comparison != null && comparison > 0;
  }

  public boolean isLess(){

    return comparison != null && comparison < 0;
  }

  public boolean isEqual(){

    return comparison != null && comparison == 0;
  }

  public boolean isGreaterOrEqual(){

    return comparison != null && comparison >= 0;
  }

  public boolean isLessOrEqual(){

    return comparison != null && comparison <= 0;
  }
}
