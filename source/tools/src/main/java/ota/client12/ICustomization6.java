package ota.client12  ;

import com4j.*;

/**
 * Customization services, such as adding users to user groups, maintaining user-defined fields, configuring user access privileges.
 */
@IID("{30117F50-091D-4F7B-8236-48353BA74006}")
public interface ICustomization6 extends ota.client12.ICustomization5 {
  // Methods:
  /**
   * <p>
   * For HP use. Returns the ICustomizationBusinessViews object.
   * </p>
   * <p>
   * Getter method for the COM property "BusinessViews"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(37) //= 0x25. The runtime will prefer the VTID if present
  @VTID(43)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject businessViews();


  // Properties:
}
