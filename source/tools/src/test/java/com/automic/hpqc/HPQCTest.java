/*package com.automic.hpqc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.automic.hpqc.actions.LoginAction;
import com.automic.hpqc.actions.LogoutAction;
import com.automic.hpqc.actions.service.DomainsService;
import com.automic.hpqc.actions.service.EntityService;
import com.automic.hpqc.actions.service.HPQCUserService;
import com.automic.hpqc.actions.service.HpqcQueryService;
import com.automic.hpqc.actions.service.LockService;
import com.automic.hpqc.actions.service.ReadEntityService;
import com.automic.hpqc.actions.service.UnlockService;
import com.automic.hpqc.exception.HpqcException;
import com.automic.hpqc.model.Domains;
import com.automic.hpqc.model.Entities;
import com.automic.hpqc.model.Entity;
import com.automic.hpqc.model.Fields;
import com.automic.hpqc.model.Users;

*//**
 * Unit test for simple App.
 *//*

public class HPQCTest {
                        * 
                        * private static final Logger LOGGER = LogManager.getLogger(HPQCTest.class);
                        * 
                        * private static final String BASEURL = "http://192.168.112.31:8080"; private static String
                        * cookie; private int responseCode = -1; private String domain = "TEST"; private String project
                        * = "Sandbox_Test"; private List<String> argsList = null;
                        * 
                        * @Rule public ExpectedException expectedException = ExpectedException.none();
                        * 
                        * @Before public void login() { System.out.println("Running login"); argsList = new
                        * ArrayList<String>(); argsList.add("LOGIN"); argsList.add(BASEURL); argsList.add("60000");
                        * argsList.add("6000"); argsList.add("sumits"); argsList.add("init");
                        * 
                        * LoginAction userAction = null; try { userAction = new LoginAction(argsList.toArray(new
                        * String[argsList.size()])); responseCode = userAction.executeAction(); cookie =
                        * userAction.printcookies(); System.out.println("------------------------------------------"); }
                        * catch (Exception e) { LOGGER.error("Error occured while running maven test ", e); responseCode
                        * = -1; }
                        * 
                        * assertEquals(0, responseCode);
                        * 
                        * }
                        * 
                        * @After public void logout() {
                        * System.out.println("------------------------------------------");
                        * System.out.println("Running logout");
                        * 
                        * argsList = new ArrayList<String>(); argsList.add("LOGOUT"); argsList.add(BASEURL);
                        * argsList.add("60000"); argsList.add("6000"); argsList.add(cookie);
                        * 
                        * LogoutAction userAction = null;
                        * 
                        * try { userAction = new LogoutAction(argsList.toArray(new String[argsList.size()]));
                        * responseCode = userAction.executeAction();
                        * 
                        * } catch (Exception e) { LOGGER.error("Error occured while running maven test ", e);
                        * responseCode = -1; } assertEquals(0, responseCode);
                        * 
                        * }
                        * 
                        * @Test public void test1DomainAndproject() { System.out.println("Running Test for getDomains");
                        * argsList = new ArrayList<String>(); argsList.add(""); argsList.add(BASEURL);
                        * argsList.add("60000"); argsList.add("6000"); argsList.add(cookie);
                        * 
                        * // System.out.println(argsList.toString());
                        * 
                        * DomainsService domainService = null;
                        * 
                        * try { domainService = new DomainsService(argsList.toArray(new String[argsList.size()]),
                        * argsList.size()); Domains domains = domainService.getDomainAndProject();
                        * System.out.println(domains);
                        * 
                        * responseCode = (domains != null) ? 0 : -1; } catch (HpqcException e) {
                        * LOGGER.error("Error occured while running maven test ", e); responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test2entityFields() { System.out.println("Running Test for entityFields ");
                        * argsList = new ArrayList<String>();
                        * 
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * EntityService entityService = null;
                        * 
                        * try { entityService = new EntityService(argsList.toArray(new String[argsList.size()]),
                        * argsList.size()); Fields fields = entityService.getFieldsForEntity("defect", false);
                        * System.out.println(fields); responseCode = (fields != null) ? 0 : -1; } catch (HpqcException
                        * e) { LOGGER.error("Error occured while running maven test ", e); responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test3getListAssociatedWithEntity() {
                        * System.out.println("Running test for getListAssociatedWithEntity "); argsList = new
                        * ArrayList<String>();
                        * 
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * EntityService entityService = null;
                        * 
                        * try { entityService = new EntityService(argsList.toArray(new String[argsList.size()]),
                        * argsList.size()); com.automic.hpqc.model.Lists lists =
                        * entityService.getListAssociatedWithEntity("defect"); System.out.println(lists); responseCode =
                        * (lists != null) ? 0 : -1; } catch (HpqcException e) {
                        * LOGGER.error("Error occured while running maven test ", e); responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test4ReadDefect() {
                        * 
                        * try { System.out.println("Running Read defect"); argsList = new ArrayList<String>();
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * ReadEntityService readService = null; readService = new ReadEntityService(argsList.toArray(new
                        * String[argsList.size()]), argsList.size()); Entity entity = readService.readEntity("defects",
                        * "100"); System.out.println(entity); responseCode = (entity != null) ? 0 : -1; } catch
                        * (Exception e) { LOGGER.error("Error occured while running maven test ", e); responseCode = -1;
                        * }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test5UserLists() {
                        * 
                        * try { System.out.println("Running Test for User Lists"); argsList = new ArrayList<String>();
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * HPQCUserService userService = null; userService = new HPQCUserService(argsList.toArray(new
                        * String[argsList.size()]), argsList.size()); Users users = userService.getUserList(domain,
                        * project); System.out.println(users); responseCode = (users != null) ? 0 : -1; } catch
                        * (Exception e) { LOGGER.error("Error occured while running maven test ", e); responseCode = -1;
                        * }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test6QueryEntity() {
                        * 
                        * try { System.out.println("Running Test for query entity"); argsList = new ArrayList<String>();
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * HpqcQueryService queryEntityService = null; queryEntityService = new
                        * HpqcQueryService(argsList.toArray(new String[argsList.size()]), argsList.size()); Entities
                        * entities = queryEntityService.queryArtifact("defects", "id[>30]", "owner,severity", 2);
                        * System.out.println(entities); responseCode = (entities != null) ? 0 : -1; } catch (Exception
                        * e) { LOGGER.error("Error occured while running maven test ", e); responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test7LockService() {
                        * 
                        * try { System.out.println("Running Test for lock service"); argsList = new ArrayList<String>();
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * LockService lockService = null; lockService = new LockService(argsList.toArray(new
                        * String[argsList.size()]), argsList.size()); boolean status =
                        * lockService.putLockOnDefect("100"); System.out.println(status); responseCode = (status) ? 0 :
                        * -1; } catch (Exception e) { LOGGER.error("Error occured while running maven test ", e);
                        * responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test8UnLockService() {
                        * 
                        * try { System.out.println("Running Test for unlock service"); argsList = new
                        * ArrayList<String>(); argsList.add(""); argsList.add(BASEURL); argsList.add("60000");
                        * argsList.add("6000"); argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * UnlockService unlockService = null; unlockService = new UnlockService(argsList.toArray(new
                        * String[argsList.size()]), argsList.size()); boolean status =
                        * unlockService.removeLockOnDefect("100"); System.out.println(status); responseCode = (status) ?
                        * 0 : -1; } catch (Exception e) { LOGGER.error("Error occured while running maven test ", e);
                        * responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        * 
                        * @Test public void test9getListAssociatedWithDefect() {
                        * System.out.println("Running test for get list associated with defects "); argsList = new
                        * ArrayList<String>();
                        * 
                        * argsList.add(""); argsList.add(BASEURL); argsList.add("60000"); argsList.add("6000");
                        * argsList.add(domain); argsList.add(project); argsList.add(cookie);
                        * 
                        * EntityService entityService = null;
                        * 
                        * try { entityService = new EntityService(argsList.toArray(new String[argsList.size()]),
                        * argsList.size()); com.automic.hpqc.model.Lists lists =
                        * entityService.getListAssociatedWithDefect(domain, project, "Severity");
                        * System.out.println(lists); responseCode = (lists != null) ? 0 : -1; } catch (HpqcException e)
                        * { LOGGER.error("Error occured while running maven test ", e); responseCode = -1; }
                        * 
                        * assertEquals(0, responseCode); }
                        
}
*/