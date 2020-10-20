package ota.client12  ;

import com4j.*;

/**
 * For HP use. _ISupportGetChildrenLists Interface.
 */
@IID("{571138B3-FBBE-4B57-97F5-B61E325AEFBA}")
public interface _ISupportGetChildrenLists extends Com4jObject {
  // Methods:
  /**
   * <p>
   * For HP use. Returns children factory lists from the given parents
   * </p>
   * @param filter Mandatory ota.client12.ITDFilter parameter.
   * @param parents Mandatory ota.client12.IList parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @VTID(3)
  ota.client12.IList getChildrenLists(
    ota.client12.ITDFilter filter,
    ota.client12.IList parents);


  // Properties:
}
