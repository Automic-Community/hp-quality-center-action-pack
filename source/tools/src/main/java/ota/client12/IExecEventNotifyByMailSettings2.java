package ota.client12  ;

import com4j.*;

/**
 * Represents the notification to be sent by e-mail after a test has completed its run.
 */
@IID("{89309A1B-2F3C-4D13-922D-7BC5D7B6F829}")
public interface IExecEventNotifyByMailSettings2 extends ota.client12.IExecEventNotifyByMailSettings {
  // Methods:
  /**
   * <p>
   * The CCTos' e-mail addresses.
   * </p>
   * <p>
   * Getter method for the COM property "CCTo"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(14)
  java.lang.String ccTo();


  /**
   * <p>
   * The CCTos' e-mail addresses.
   * </p>
   * <p>
   * Setter method for the COM property "CCTo"
   * </p>
   * @param pVal Mandatory java.lang.String parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(15)
  void ccTo(
    java.lang.String pVal);


  // Properties:
}
