package ota.client12  ;

import com4j.*;

/**
 * For HP use. Represents traceability matrix row for a single entity.
 */
@IID("{F2751BD3-9951-4856-AFE8-9EF40CCCD692}")
public interface ITraceabilityMatrixRow extends Com4jObject {
  // Methods:
  /**
   * <p>
   * The ID of the entity of which the row is related to.
   * </p>
   * <p>
   * Getter method for the COM property "EntityID"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  int entityID();


  /**
   * <p>
   * Returns a copy of The list of the traceability matrix relation results, related to the row.
   * </p>
   * <p>
   * Getter method for the COM property "Results"
   * </p>
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  ota.client12.IList results();


  @VTID(8)
  @ReturnValue(type=NativeType.VARIANT,defaultPropertyThrough={ota.client12.IList.class})
  java.lang.Object results(
    int index);

  // Properties:
}
