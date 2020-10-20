<?xml version="1.0" encoding="UTF-8"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<!-- $Id$ -->
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
  <xsl:param name="versionParam" select="'1.0'"/> 
  <!-- ========================= -->
  <!-- root element: Testset -->
  <!-- ========================= -->
  
  <xsl:template name="split_value">
      <xsl:param name="value"/>
      <xsl:param name="max_length"/>
      <xsl:variable name="ret">
          <xsl:value-of select="substring($value, 1, $max_length)"/>
          <xsl:if test="string-length($value) &gt; $max_length">
              <xsl:value-of select="'&#x200b;'"/>
              <xsl:call-template name="split_value">
                  <xsl:with-param
                      name="value"
                      select="substring($value, $max_length + 1)"
                  />
                  <xsl:with-param
                      name="max_length"
                      select="$max_length"
                  />
              </xsl:call-template>
          </xsl:if>
      </xsl:variable>
     <xsl:value-of select="$ret"/>
  </xsl:template>

  <xsl:template match="Testset">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="12pt" font-weight="bold" text-align="center" space-after="5mm">TestSet Execution Report
            </fo:block>
          <fo:block font-size="10pt" font-weight="bold" space-after="5mm">TestSet Path: <xsl:value-of select="FolderPath"/>
            </fo:block>
          <fo:block font-size="10pt" font-weight="bold" space-after="5mm">TestSet Name: <xsl:value-of select="Name"/>          
          </fo:block>
          <fo:block font-size="8pt">
            <fo:table table-layout="fixed" width="100%" border-collapse="separate">
              <fo:table-column column-width="30%"  border-style="solid" border-width=".6pt"/>
              <fo:table-column column-width="17%" border-style="solid" border-width=".6pt"/>
              <fo:table-column column-width="17%" border-style="solid" border-width=".6pt"/>
              <fo:table-column column-width="18%" border-style="solid" border-width=".6pt"/>
              <fo:table-column column-width="18%" border-style="solid" border-width=".6pt"/>

            <fo:table-header>
	       <fo:table-row text-align="center">
	       <xsl:attribute name="font-weight">bold</xsl:attribute>
	          <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
	             <fo:block>
	                 Test Name
	             </fo:block>
	          </fo:table-cell>
	          <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
	             <fo:block>
	                 Status
	             </fo:block>
	          </fo:table-cell>
	          <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
	             <fo:block>
	                 Execution Date
	             </fo:block>
	          </fo:table-cell>
	          <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
	             <fo:block>
	                 Tester
	             </fo:block>
	          </fo:table-cell>
	          <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
	             <fo:block>
	                 Owner
	             </fo:block>
	          </fo:table-cell>
	          
	       </fo:table-row>
	   </fo:table-header>

              <fo:table-body>
                <xsl:apply-templates select="Fields"/>
              </fo:table-body>

            </fo:table>
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  <!-- ========================= -->
  <!-- child element: member     -->
  <!-- ========================= -->
  <xsl:template match="Fields">
    <fo:table-row text-align="center">
      <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
        <xsl:if test="string-length(Field[@Name='name']/Value) &gt; 25">
     	  	<xsl:attribute name="text-align"> left </xsl:attribute>
        </xsl:if>
        <fo:block>
           <xsl:call-template name="split_value">
                <xsl:with-param
                    name="value"
                    select="Field[@Name='name']/Value"
                />
                <xsl:with-param
                    name="max_length"
                    select="number(25)"
                />
           </xsl:call-template>
        </fo:block>
      </fo:table-cell>
      <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
          <xsl:if test="Field[@Name='status']/Value = 'Failed'">
            <xsl:attribute name="color"> red </xsl:attribute>
          </xsl:if>        
        <fo:block >
        <xsl:call-template name="split_value">
                <xsl:with-param
                    name="value"
                    select="Field[@Name='status']/Value"
                />
                <xsl:with-param
                    name="max_length"
                    select="number(13)"
                />
       </xsl:call-template>          
        </fo:block>
      </fo:table-cell>
      <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
        <fo:block >
          <xsl:value-of select="Field[@Name='exec-date']/Value"/> <xsl:value-of select="' '"/> <xsl:value-of select="Field[@Name='exec-time']/Value"/>
        </fo:block>
      </fo:table-cell>
      <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
        <xsl:if test="string-length(Field[@Name='actual-tester']/Value) &gt; 15">
     	  	<xsl:attribute name="text-align"> left </xsl:attribute>
        </xsl:if>        
        <fo:block >        
        <xsl:call-template name="split_value">
                <xsl:with-param
                    name="value"
                    select="Field[@Name='actual-tester']/Value"
                />
                <xsl:with-param
                    name="max_length"
                    select="number(15)"
                />
       </xsl:call-template>          
       </fo:block>
      </fo:table-cell>
      <fo:table-cell padding="1pt" border-style="solid" border-width=".2pt">
        <xsl:if test="string-length(Field[@Name='owner']/Value) &gt; 15">
     	  	<xsl:attribute name="text-align"> left </xsl:attribute>
        </xsl:if>        
        <fo:block >
        <xsl:call-template name="split_value">
                <xsl:with-param
                    name="value"
                    select="Field[@Name='owner']/Value"
                />
                <xsl:with-param
                    name="max_length"
                    select="number(15)"
                />
       </xsl:call-template>
        </fo:block>
      </fo:table-cell>
   </fo:table-row>
  </xsl:template>
</xsl:stylesheet>
