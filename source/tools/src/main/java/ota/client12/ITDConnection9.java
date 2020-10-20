package ota.client12  ;

import com4j.*;

/**
 * Represents a single server connection.
 */
@IID("{ABA2DE60-E7A4-4D38-B5B5-0A4D5F91705A}")
public interface ITDConnection9 extends ota.client12.ITDConnection8 {
  // Methods:
  /**
   * <p>
   * Checks if external authentication enabled on server
   * </p>
   * <p>
   * Getter method for the COM property "ExternalAuthenticationEnabled"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(215) //= 0xd7. The runtime will prefer the VTID if present
  @VTID(219)
  boolean externalAuthenticationEnabled();


  /**
   * <p>
   * Creates application session on server, similar to login operation but for SSO configuration.
   * </p>
   */

  @DISPID(216) //= 0xd8. The runtime will prefer the VTID if present
  @VTID(220)
  void createSession();


  /**
   * <p>
   * For HP use. Establishes application session on the server in SSO env. Returns the list of project descriptors visible to the authenticating user.
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(217) //= 0xd9. The runtime will prefer the VTID if present
  @VTID(221)
  ota.client12.IList createSessionEx();


  @VTID(221)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object createSessionEx(
    int index);

  /**
   * <p>
   * Apply client certificate to connection, the parameter should be valid CERT_CONTEXT structure.
   * </p>
   * @param certContext Mandatory java.lang.Object parameter.
   */

  @DISPID(218) //= 0xda. The runtime will prefer the VTID if present
  @VTID(222)
  void applyClientCert(
    @MarshalAs(NativeType.VARIANT) java.lang.Object certContext);


  /**
   * <p>
   * Set execution mode to WebClient
   * </p>
   * @param executionMode Mandatory ota.client12.TDAPI_EXECUTION_MODES parameter.
   */

  @DISPID(219) //= 0xdb. The runtime will prefer the VTID if present
  @VTID(223)
  void setExecutionMode(
    ota.client12.TDAPI_EXECUTION_MODES executionMode);


  /**
   * <p>
   * method Applycookie
   * </p>
   * @param cookie Mandatory java.lang.String parameter.
   */

  @DISPID(220) //= 0xdc. The runtime will prefer the VTID if present
  @VTID(224)
  void applyCookie(
    java.lang.String cookie);


  /**
   * <p>
   * Get Compatibility of OTA to Server
   * </p>
   * <p>
   * Getter method for the COM property "IsServerCompatible"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(221) //= 0xdd. The runtime will prefer the VTID if present
  @VTID(225)
  boolean isServerCompatible();


  /**
   * <p>
   * method InitAndCheckServerCompatibility
   * </p>
   * @param serverName Mandatory java.lang.String parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(222) //= 0xde. The runtime will prefer the VTID if present
  @VTID(226)
  boolean initAndCheckServerCompatibility(
    java.lang.String serverName);


  // Properties:
}
