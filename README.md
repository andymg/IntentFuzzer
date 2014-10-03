IntentFuzzer
====================

IntentFuzzer is inspired by the tool(https://www.isecpartners.com/tools/mobile-security/intent-fuzzer.aspx)
developed by iSECpartners(www.isecpartners.com).
You can specify an application,then either fuzz a single component or all components!
For a single component, just click an item listed. While click the "Null Fuzz All" button for all components!

Usage:
adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -e type all -n com.android.intentfuzzer/com.android.intentfuzzer.MainActivity
