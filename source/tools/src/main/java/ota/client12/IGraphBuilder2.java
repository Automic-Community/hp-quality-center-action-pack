package ota.client12  ;

import com4j.*;

/**
 * Services for creating graphs by Table name.
 */
@IID("{9D495F53-26E3-4A98-8EF1-DF7A7F65B53C}")
public interface IGraphBuilder2 extends ota.client12.IGraphBuilder {
  // Methods:
  /**
   * <p>
   * For HP use. Create an IGraphDefinition2 object by Table name.
   * </p>
   * @param tableName Mandatory java.lang.String parameter.
   * @param graphType Mandatory int parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject createGraphDefinitionByTableName(
    java.lang.String tableName,
    int graphType);


  /**
   * <p>
   * Build the graph.
   * </p>
   * @param pGraphDef Mandatory ota.client12.IGraphDefinition parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String startBuildGraph(
    ota.client12.IGraphDefinition pGraphDef);


  /**
   * <p>
   * Build the graph.
   * </p>
   * @param input Mandatory java.lang.String parameter.
   * @param pGraphDef Mandatory ota.client12.IGraphDefinition parameter.
   * @return  Returns a value of type ota.client12.IGraph
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  ota.client12.IGraph getGraphResultFromString(
    java.lang.String input,
    ota.client12.IGraphDefinition pGraphDef);


  // Properties:
}
