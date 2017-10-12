package org.xitikit.blue.gifnoc.sporp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * There is no functionality currently implemented to allow a user to
 * change their email address nor username in Azure AD B2C. This class
 * is a placeholder as a reminder of the dream that perchance they will
 * fix this BUG, and implement this critical piece of functionality.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailPolicy{

  /**
   * Indicates that this policy is NOT going to be used if true.
   */
  private boolean disabled = true;
}
