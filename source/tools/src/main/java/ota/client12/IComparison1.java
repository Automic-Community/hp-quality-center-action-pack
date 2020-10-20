package ota.client12  ;

import com4j.*;

/**
 * For HP use. Represents a Comparison.
 */
@IID("{8E422E74-3D8B-4507-B992-C5224F2742D0}")
public interface IComparison1 extends ota.client12.IComparison {
  // Methods:
  /**
   * <p>
   * Get a Comparison detailed report as a CSV String - this string can be saved by the UI/OTA Script to a file
   * </p>
   * @param comparisonSettingsList Mandatory ota.client12.IList parameter.
   * @return  Returns a value of type ota.client12.IList
   */

  @DISPID(28) //= 0x1c. The runtime will prefer the VTID if present
  @VTID(38)
  ota.client12.IList getCSVReportOnLastComparison(
    ota.client12.IList comparisonSettingsList);


  // Properties:
}
