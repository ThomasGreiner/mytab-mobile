/*
 * Copyright (c) 2011 Thomas Greiner (http://www.greinr.com). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following disclaimer
 *      in the documentation and/or other materials provided with the
 *      distribution.
 *    * The name of Thomas Greiner and those of other
 *      contributors may not be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.devstar.mytab;

import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import at.devstar.mytab.logic.PreferencesManager;

public class OptionsActivities {
	private final static String URI = "at.devstar.mytab.OptionsActivities";
	
	public static class AccountActivity extends PreferenceActivity {
		private boolean isLoaded = false;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}
		
		@Override
		public void onResume() {
			super.onResume();
			if(!isLoaded) {
				addPreferencesFromResource(R.xml.options_account);
				PreferenceManager.setDefaultValues(this, R.xml.options_account, false);
				isLoaded = true;
			}
		}
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main, menu);
			return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	Intent intent = null;
	    	boolean r = true;
	    	
	    	switch(item.getItemId()) {
	    		case R.id.mytab:
	    			intent = new Intent(this, MainActivity.class);
	    			break;
	    		case R.id.options_feeds:
	    			intent = new Intent(this, OptionsActivities.FeedsActivity.class);
	    			break;
	    		case R.id.options_info:
	    			intent = new Intent(this, OptionsActivities.InfoActivity.class);
	    			break;
	    		default:
	        		r = super.onOptionsItemSelected(item);
	    	}
	    	
	    	if(intent != null) {
		    	startActivity(intent);
	    	}
	    	
	    	return r;
	    }
	}
	
	public static class FeedsActivity extends PreferenceActivity {
		private boolean isLoaded = false;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}
		
		@Override
		public void onResume() {
			super.onResume();
			if(!isLoaded) {
				addPreferencesFromResource(R.xml.options_feeds);
				PreferenceManager.setDefaultValues(this, R.xml.options_feeds, false);
				isLoaded = true;
			}
			
			boolean enablePersonalFeeds = (PreferencesManager.getYouTubeUsername().length() > 0);
			PreferenceScreen screen = getPreferenceScreen();
			Preference prefFavorites = screen.findPreference("show_favorites");
			prefFavorites.setEnabled(enablePersonalFeeds);
			Preference prefSubscriptions = screen.findPreference("show_newsubscriptionvideos");
			prefSubscriptions.setEnabled(enablePersonalFeeds);
		}
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main, menu);
			return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	Intent intent = null;
	    	boolean r = true;
	    	
	    	switch(item.getItemId()) {
	    		case R.id.mytab:
	    			intent = new Intent(this, MainActivity.class);
	    			break;
	    		case R.id.options_account:
	    			intent = new Intent(this, OptionsActivities.AccountActivity.class);
	    			break;
	    		case R.id.options_info:
	    			intent = new Intent(this, OptionsActivities.InfoActivity.class);
	    			break;
	    		default:
	        		r = super.onOptionsItemSelected(item);
	    	}
	    	
	    	if(intent != null) {
		    	startActivity(intent);
	    	}
	    	
	    	return r;
	    }
	}
	
	public static class InfoActivity extends Activity {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.info);
		}
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main, menu);
			return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	Intent intent = null;
	    	boolean r = true;
	    	
	    	switch(item.getItemId()) {
	    		case R.id.mytab:
	    			intent = new Intent(this, MainActivity.class);
	    			break;
	    		case R.id.options_account:
	    			intent = new Intent(this, OptionsActivities.AccountActivity.class);
	    			break;
	    		case R.id.options_feeds:
	    			intent = new Intent(this, OptionsActivities.FeedsActivity.class);
	    			break;
	    		default:
	        		r = super.onOptionsItemSelected(item);
	    	}
	    	
	    	if(intent != null) {
		    	startActivity(intent);
	    	}
	    	
	    	return r;
	    }
	    
	    public void click(View v) {
			Log.d(URI+"$InfoActivity", "clicked");
			URL url = null;
			try {
				switch(v.getId()) {
					case R.id.options_info_twitter:
						url = new URL(getString(R.string.url_twitter));
						break;
					case R.id.options_info_gplus:
						url = new URL(getString(R.string.url_google_plus));
						break;
					case R.id.options_info_gcode:
						url = new URL(getString(R.string.url_google_code));
						break;
					case R.id.options_info_homepage:
						url = new URL(getString(R.string.url_homepage));
						break;
					default:
						throw new NullPointerException();
				}
				
				Intent i = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse(url.toString())
				);
				startActivity(i);
			}
			catch(MalformedURLException e) {
				Log.e(URI+"$InfoActivity", "malformed URL: "+e.getMessage());
			}
			catch(NullPointerException e) {
				Log.e(URI+"$InfoActivity", "id not found: "+v.getId());
			}
			
		}
	}
}