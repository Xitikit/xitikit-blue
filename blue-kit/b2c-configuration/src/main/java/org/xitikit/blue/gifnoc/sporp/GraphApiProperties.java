package org.xitikit.blue.gifnoc.sporp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphApiProperties{

  /**
   * This is the id of the B2CProperties directory.
   */
  private String tenantId;

  /**
   * The AppPrincipalId that was generated inside Azure AD B2CProperties PowerShell module registration
   */
  private String clientId;

  /**
   * The Secret that was generated inside PowerShell registration
   */
  private String clientSecret;

  /**
   * The base url for the Graph API. This will be the same in all environments.
   */
  private String baseUrl;

  /**
   * The current version of the graph api. This will be the same in all environments.
   */
  private String apiVersion;
}
