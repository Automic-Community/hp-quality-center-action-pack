package ota.client12  ;

import com4j.*;

/**
 * Customization services, such as adding users to user groups, maintaining user-defined fields, configuring user access privileges.
 */
@IID("{D42DAAFA-71DF-46EC-8561-408C66F31AAD}")
public interface ICustomization5 extends ota.client12.ICustomization4 {
  // Methods:
  /**
   * <p>
   * Explicitly increments the server's customization version.
   * </p>
   */

  @DISPID(36) //= 0x24. The runtime will prefer the VTID if present
  @VTID(42)
  void incrementServerVersion();


  // Properties:
}
