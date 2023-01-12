package com.twc.ios.app.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.twc.ios.app.charlesfunctions.CharlesProxy;
import com.twc.ios.app.functions.Functions;
import com.twc.ios.app.general.ParseForVideoOrderedList;
import com.twc.ios.app.general.TestBase;
import com.twc.ios.app.general.TwcIosBaseTest;
import com.twc.ios.app.general.Utils;
import com.twc.ios.app.pages.AddressScreen;
import com.twc.ios.app.pages.AndroidAddressScreen;
import com.twc.ios.app.pages.AndroidDailyNavTab;
import com.twc.ios.app.pages.AndroidHomeNavTab;
import com.twc.ios.app.pages.AndroidHourlyNavTab;
import com.twc.ios.app.pages.AndroidNewsCardScreen;
import com.twc.ios.app.pages.AndroidRadarNavTab;
import com.twc.ios.app.pages.AndroidSeasonalHubCardScreen;
import com.twc.ios.app.pages.AndroidSettingsScreen;
import com.twc.ios.app.pages.AndroidVideoNavTab;
import com.twc.ios.app.pages.DailyNavTab;
import com.twc.ios.app.pages.HomeNavTab;
import com.twc.ios.app.pages.HourlyNavTab;
import com.twc.ios.app.pages.NewsCardScreen;
import com.twc.ios.app.pages.PlanningCardScreen;
import com.twc.ios.app.pages.RadarNavTab;
import com.twc.ios.app.pages.SeasonalHubCardScreen;
import com.twc.ios.app.pages.SettingsScreen;
import com.twc.ios.app.pages.VideoNavTab;

import java.io.File;

import org.testng.annotations.Listeners;

import org.testng.annotations.BeforeClass;

//import ru.yandex.qatools.allure.annotations.Title;
import io.qameta.allure.Description;

@Listeners(value = com.twc.ios.app.general.MyTestListenerAdapter.class)
public class AndroidCriteoAndAmazonBidTest extends TwcIosBaseTest {

	private static final String CONFIG_FILE_PATH = "charles_common.config";
	private static final String BN_SEVERE1_CONFIG_FILE_PATH = "BNSevere1charles_common.config";
	private static final String BN_SEVERE2_CONFIG_FILE_PATH = "BNSevere2charles_common.config";
	private static final String CRITEO_CONFIG_FILE_PATH = "Criteocharles_common.config";

	// public static CharlesProxy proxy;
	public File configFile;
	AndroidHourlyNavTab hrTab;
	AndroidDailyNavTab dTab;
	AndroidHomeNavTab hmTab;
	AndroidRadarNavTab rTab;
	AndroidVideoNavTab vTab;
	AndroidAddressScreen addrScreen;
	AndroidSeasonalHubCardScreen sScreen;
	AndroidNewsCardScreen nScreen;
	AndroidSettingsScreen stScreen;

	@BeforeClass(alwaysRun = true)
	@Description("BeforeClass")
	public void beforeClass() {
		System.out.println("****** Criteo and Amazon Bid Parameters validation Test Started");
		logStep("****** Criteo and Amazon Bid Parameters validation Test Started");
		// this.configFile = this.charlesGeneralConfigFile(CONFIG_FILE_PATH);
		// proxy = new CharlesProxy("localhost", 8111, CONFIG_FILE_PATH);
		//this.configFile = this.rewriteRuleToOverrideGeoIpCountry(CRITEO_CONFIG_FILE_PATH, "US");
		this.configFile = this.rewriteRuleToEnableUSAWhenNoTunnelBearAndroid(CRITEO_CONFIG_FILE_PATH, "usa", "US", "WA");
		proxy = new CharlesProxy("localhost", 8111, CRITEO_CONFIG_FILE_PATH);
		proxy.startCharlesProxyWithUI();
		proxy.disableRewriting();
		proxy.stopRecording();
		proxy.disableMapLocal();
	}

	
	
	@Test(priority = 0)
	@Description("Updating Device Proxy Details and Launch the App")
	public void beforeTest() throws Exception {
		System.out.println("==============================================");
		System.out.println("****** Updating Device Proxy Details and Launch the App test case Started");
		logStep("****** Updating Device Proxy Details and Launch the App test case Started");
		// Preconditions
		Functions.capabilities();
		//Functions.Appium_Autostart();
		//Utils.getCurrentMacIPAddressAndSetandroidProxy(true, true);
		// Enable rewriting on Charles install/launch TWC to rewrite geoipcountry to US
		proxy.enableRewriting();
		proxy.startRecording();
		proxy.clearCharlesSession();
		Functions.archive_folder("Charles");
		//Functions.launchtheApp("true");
		Functions.launchtheAndroidApp();
		System.out.println("App launched ");
		logStep("App launched ");
		hrTab = new AndroidHourlyNavTab(Ad);
		dTab = new AndroidDailyNavTab(Ad);
		hmTab = new AndroidHomeNavTab(Ad);
		rTab = new AndroidRadarNavTab(Ad);
		vTab = new AndroidVideoNavTab(Ad);
		addrScreen = new AndroidAddressScreen(Ad);
		sScreen = new AndroidSeasonalHubCardScreen(Ad);
		nScreen = new AndroidNewsCardScreen(Ad);
		stScreen = new AndroidSettingsScreen(Ad);
	}

	
	
	@Test(priority = 525, enabled = true)
	@Description("Enabling Preconfigurations to validate amazon bid id's Interstitial Call")
	public void enable_PreConditions_to_Validate_amazon_bid_ids_Interstitial_Call() throws Exception {
		System.out.println("==============================================");
		System.out.println(
				"=========================== Enabling Preconfigurations to validate amazon bid id's of Interstitial Call ====================");
		System.out
				.println("****** Enabling Preconfigurations to validate amazon bid id's of Interstitial Call Started");
		logStep("****** Enabling Preconfigurations to validate amazon bid id's of Interstitial Call Started");
//		stScreen.select_Airlock_Branch("UnlimitedInterstitialAutomation02");
		stScreen.select_Airlock_UserGroup("unlimitedinterstitials");
		Functions.archive_folder("Charles");
		//stScreen.select_Airlock_Branch("HDB");
		//stScreen.select_Airlock_UserGroup("Criteo");
		Functions.close_launchAppAndroid();
		proxy.clearCharlesSession();
		Functions.close_launchAppAndroid();
		TestBase.waitForMilliSeconds(15000);
		Functions.put_Background_launchAndroid(15);
		proxy.getXml();
		Utils.createXMLFileForCharlesSessionFile();
		// Utils.get_v3_wx_forecast_daily_15day_data();
				
	}
	
	@Test(priority = 526, enabled = true)
	@Description("Verify amazon aax Interstitials preload ad call")
	public void Verify_amazon_aax_preload_Intersitials_adcall() throws Exception {
		System.out.println("==============================================");
		System.out
				.println("=========================== amazon aax Interstitials preload ad call ====================");
		System.out.println("****** amazon aax Interstitials preload ad call validation Started");
		logStep("****** amazon aax Interstitials preload ad call validation Started");
		Utils.verifyAAX_SlotId("Smoke", "Interstitials");
	}
	
	@Test(priority = 527, enabled = true)
	@Description("Verify Interstitials ad call amazon bid id")
	public void Verify_Interstitials_ad_call_amazon_bid_id() throws Exception {
		System.out.println("==============================================");
		System.out.println("=========================== Interstitials ad call amazon bid id ====================");

		System.out.println("****** Interstitials ad call amazon bid id validation Started");
		logStep("****** Interstitials ad call amazon bid id validation Started");
		//Utils.validate_aax_bid_value_with_gampad_bid_value("Smoke", "Interstitials", true);
		Utils.validate_aax_bid_value_with_gampad_bid_value("Smoke", "Interstitials", true, true);
	}
	
	@Test(priority = 540, enabled = true)
	@Description("Verify cpm parameter of Criteo SDK inapp v2 call with Interstitials call")
	public void Verify_Criteo_SDK_inapp_v2_Call_cpm_parameter_with_Interstitials_gampad_call() throws Exception {
		System.out.println("==============================================");
		System.out.println(
				"=========================== Criteo SDK invapp v2 call cpm parameter with Interstitials call====================");

		System.out
				.println("****** Criteo SDK invapp v2 call cpm parameter with Interstitials call validation Started");
		logStep("****** Criteo SDK invapp v2 call cpm parameter with Interstitials call validation Started");
		Utils.validate_Criteo_SDK_inapp_v2_call_param_value_with_gampad_param_value("Smoke", "Interstitials", "cpm", true);
	}

	@Test(priority = 541, enabled = true)
	@Description("Verify size parameter of Criteo SDK inapp v2 call with Interstitials call")
	public void Verify_Criteo_SDK_inapp_v2_Call_size_parameter_with_Interstitials_gampad_call() throws Exception {
		System.out.println("==============================================");
		System.out.println(
				"=========================== Criteo SDK invapp v2 call size parameter with Interstitials call====================");

		System.out
				.println("****** Criteo SDK invapp v2 call size parameter with Interstitials call validation Started");
		logStep("****** Criteo SDK invapp v2 call size parameter with Interstitials call validation Started");
		Utils.validate_Criteo_SDK_inapp_v2_call_param_value_with_gampad_param_value("Smoke", "Interstitials", "size", true);
	}

	@Test(priority = 542, enabled = true)
	@Description("Verify displayUrl parameter of Criteo SDK inapp v2 call with Interstitials call")
	public void Verify_Criteo_SDK_inapp_v2_Call_displayUrl_parameter_with_Interstitials_gampad_call()
			throws Exception {
		System.out.println("==============================================");
		System.out.println(
				"=========================== Criteo SDK invapp v2 call displayUrl parameter with Interstitials call====================");

		System.out.println(
				"****** Criteo SDK invapp v2 call displayUrl parameter with Interstitials call validation Started");
		logStep("****** Criteo SDK invapp v2 call displayUrl parameter with Interstitials call validation Started");
		Utils.validate_Criteo_SDK_inapp_v2_call_param_value_with_gampad_param_value("Smoke", "Interstitials", "displayUrl", true);
	}

}
