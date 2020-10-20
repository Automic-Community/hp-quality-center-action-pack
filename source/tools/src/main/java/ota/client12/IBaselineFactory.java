package ota.client12  ;

import com4j.*;

/**
 * For HP use. Services for managing baselines.
 */
@IID("{3D7543C1-9B84-457E-9A1A-CCB7D20663FA}")
public interface IBaselineFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * A list of IBaselineItem objects in which the entity participated.
   * </p>
   * @param pEntity Mandatory ota.client12.IBaseField parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList baselineItems(
    ota.client12.IBaseField pEntity);


  // Properties:
}
