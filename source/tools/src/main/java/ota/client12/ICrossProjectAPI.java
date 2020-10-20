package ota.client12  ;

import com4j.*;

/**
 * For HP use. Cross-Project service API.
 */
@IID("{9EF01CB3-549B-4F0B-9AD9-634916324844}")
public interface ICrossProjectAPI extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Return a list of lists. Each list contains the intersection of fields of a specific entity in the specified projects. Each entity in the specified list of entities has it's own list of fields.
   * </p>
   * @param pProjectIds Mandatory ota.client12.IList parameter.
   * @param pEntityTypes Mandatory ota.client12.IList parameter.
   * @param entityFieldComparisonStrategy Mandatory int parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  ota.client12.IList getEntitiesFieldIntersection(
    ota.client12.IList pProjectIds,
    ota.client12.IList pEntityTypes,
    int entityFieldComparisonStrategy);


  // Properties:
}
