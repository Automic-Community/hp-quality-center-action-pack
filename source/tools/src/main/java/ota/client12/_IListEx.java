package ota.client12  ;

import com4j.*;

/**
 * For HP use. _IListEx Interface.
 */
@IID("{DD5DE9AF-BAEF-4FA0-8555-DED76D4C9D2F}")
public interface _IListEx extends Com4jObject {
  // Methods:
  /**
   * <p>
   * For HP use.
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  boolean isIdListSupported();


  /**
   * <p>
   * For HP use.
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  ota.client12.IList getIdList();


  @VTID(8)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object getIdList(
    int index);

  // Properties:
}
