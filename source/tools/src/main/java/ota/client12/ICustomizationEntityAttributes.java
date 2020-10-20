package ota.client12  ;

import com4j.*;

/**
 * Represents an entity attribute.
 */
@IID("{1F32CB1E-3A39-41FF-93D0-ED827995DC98}")
public interface ICustomizationEntityAttributes extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Gets the list of all ICustomizationEntityAttribute for the specified entity.
   * </p>
   * @param entityType Mandatory java.lang.String parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  ota.client12.IList getEntityAttributes(
    java.lang.String entityType);


  // Properties:
}
