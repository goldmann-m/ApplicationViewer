package de.goldmann.android.applicationviewer.utilities;

import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class Utilities {

	/**
	 * Get all installed applications on mobile phone and return as list
	 * 
	 * @param c - context of application
	 * @return list of installed applications
	 */
	public static List<ApplicationInfo> getInstalledApplication(Context c){
		return c.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
	}
	
	public static boolean launchApp(Context c, PackageManager pm, String pkgName){
		//query intent for launching
		Intent intent = pm.getLaunchIntentForPackage(pkgName);
		
		//if intent is available
		if(intent != null) {
			try {
				c.startActivity(intent);
				return true;
			} catch (ActivityNotFoundException e) {
				// quick message notification
				Toast toast = Toast.makeText(c, "Application not found.", Toast.LENGTH_LONG);
				
				//display message
				toast.show();
			}
		}
		
		return false;
	}
}
