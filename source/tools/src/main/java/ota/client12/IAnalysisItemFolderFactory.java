package ota.client12  ;

import com4j.*;

/**
 * For HP use. Services to manage analysis item folders.
 */
@IID("{A7ECE1B7-1BC6-4E79-9494-6DA9BED9020F}")
public interface IAnalysisItemFolderFactory extends ota.client12.IBaseFactoryEx {
  // Methods:
  /**
   * <p>
   * Returns the list of those Analysis Items that are descendants of the specified folders and are displayed on at least one dashboard page.
   * </p>
   * @param pIdsList Mandatory ota.client12.IList parameter.
   * @param inPublicPagesOnly Mandatory boolean parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(17)
  ota.client12.IList getChildItemsIncludedInPages(
    ota.client12.IList pIdsList,
    boolean inPublicPagesOnly);


  /**
   * <p>
   * Returns a reference to the root of the specified tree.
   * </p>
   * @param isPublic Mandatory boolean parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(18)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getTreeRoot(
    boolean isPublic);


  /**
   * <p>
   * Returns the ID of the specified Analysis Tree Root.
   * </p>
   * @param isPublic Mandatory boolean parameter.
   * @return  Returns a value of type int
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(19)
  int getTreeRootID(
    boolean isPublic);


  /**
   * <p>
   * For HP use. Checks whether the specified Analysis Tree Root is editable.
   * </p>
   * @param isPublic Mandatory boolean parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(20)
  boolean isTreeRootEditable(
    boolean isPublic);


  // Properties:
}
