package ota.client12  ;

import com4j.*;

/**
 * Represents a single Business Process Component.
 */
@IID("{7E931198-BC71-4327-A435-3E9D36B1EBC7}")
public interface IComponent4 extends ota.client12.IComponent3 {
  // Methods:
  /**
   * <p>
   * The FacetFactory for this component.
   * </p>
   * <p>
   * Getter method for the COM property "FacetFactory"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(35) //= 0x23. The runtime will prefer the VTID if present
  @VTID(46)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject facetFactory();


  /**
   * <p>
   * For HP use. Get A String containing the names of the current component parametes seperated by comma.
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(36) //= 0x24. The runtime will prefer the VTID if present
  @VTID(47)
  java.lang.String getComponentParametersNames();


  // Properties:
}
