## Getting Started:


###### Description

The HP Quality Center Package allows developers, administrators or testers to automate HP Quality Center and HP Unified Functional Testing (UFT) from Automation Engine workflows. The contained actions support the query, creation, update or deletion of HP Quality Center defects and the execution of HP UFT testsets. 
By the automated creation of defects, administrators can be notified of failed automated deployments while testers or developers might be warned about unsuccessful smoke tests.
Furthermore, the HP Quality Center Package can execute predefined HP UFT Testsets and generate reports from them. This allows software teams to incorporate smoke tests to their deployment workflow and to continue or cancel the deployment based on the test results.
The provided actions offer the capability to add test reports, screenshots and other useful artifacts as attachments to created defects.
The HP Quality Center Package uses the HP Quality Center REST API and can therefore also target remote HP QC installations. The provided actions support both headless and promptset mode, which means that the required inputs can either be provided by promptsets or UC4 script variables.		

###### Actions

1. Login to HP Quality Center
2. Logout from HP Quality Center
3. Read a defect
4. Create a defect
5. Delete a defect
6. Update a defect
7. Add attachment to a defect
8. Add comment to a defect
9. Query HP Quality Center entity (defects, requirements, ...)
10. Query available defect fields
11. Query available editable defect fields
12. List all available projects
13. List all available users
14. Query filterable fields for defect
15. Execute a HP UFT testset
16. Generate execution report of HP UFT testset (passed/failed tests)
		
###### Compatibility:

	###### Automic:
	ECC v11.1

	###### HP Quality Center:
		HP Quality Center
		HP Quality Center 12
		HP Quality Center 11.52
		QC 12.60
	###### HP UFT:	
		HP UFT 12
		HP UFT 11.52
		UFT 14.52

###### Prerequisite:

1. Automation Engine should be installed.
2. Automic Package Manager should be installed.

###### Steps to install action pack source code:

1. Clone the code to your machine.
2. Go to the package directory.
3. Run the command apm upload in the directory which contains package.yml (source/):

Ex. **apm upload -force -u <Name>/<Department> -c <Client-id> -H <Host> -pw <Password> -S AUTOMIC -y -ia -ru**


###### Package/Action Documentation

Please refer to the link for [package documentation](source/ae/DOCUMENTATION/PCK.AUTOMIC_CA_APM.PUB.DOC.xml)

###### Third party licenses:

The third-party library and license document reference.[Third party licenses](source/ae/DOCUMENTATION/PCK.AUTOMIC_CA_APM.PUB.LICENSES.xml)

###### Useful References

1. [About Packs and Plug-ins](https://docs.automic.com/documentation/webhelp/english/AA/12.3/DOCU/12.3/Automic%20Automation%20Guides/help.htm#PluginManager/PM_AboutPacksandPlugins.htm?Highlight=Action%20packs)
2. [Working with Packs and Plug-ins](https://docs.automic.com/documentation/webhelp/english/AA/12.3/DOCU/12.3/Automic%20Automation%20Guides/help.htm#PluginManager/PM_WorkingWith.htm#link10)
3. [Actions and Action Packs](https://docs.automic.com/documentation/webhelp/english/AA/12.3/DOCU/12.3/Automic%20Automation%20Guides/help.htm#_Common/ReleaseHighlights/RH_Plugin_PackageManager.htm?Highlight=Action%20packs)
4. [PACKS Compatibility Mode](https://docs.automic.com/documentation/webhelp/english/AA/12.3/DOCU/12.3/Automic%20Automation%20Guides/help.htm#AWA/Variables/UC_CLIENT_SETTINGS/UC_CLIENT_PACKS_COMPATIBILITY_MODE.htm?Highlight=Action%20packs)
5. [Working with actions](https://docs.automic.com/documentation/webhelp/english/AA/12.3/DOCU/12.3/Automic%20Automation%20Guides/help.htm#ActionBuilder/AB_WorkingWith.htm#link4)
6. [Installing and Configuring the Action Builder](https://docs.automic.com/documentation/webhelp/english/AA/12.3/DOCU/12.3/Automic%20Automation%20Guides/help.htm#ActionBuilder/install_configure_plugins_AB.htm?Highlight=Action%20packs)

###### Distribution: 

In the distribution process, we can download the existing or updated action package from the Automation Engine by using the apm build command.
Example: **apm build -y -H AE_HOST -c 106 -u TEST/TEST -pw password -d /directory/ -o zip -v action_pack_name**
			
			
###### Copyright and License: 

Broadcom does not support, maintain or warrant Solutions, Templates, Actions and any other content published on the Community and is subject to Broadcom Community [Terms and Conditions](https://community.broadcom.com/termsandconditions)
