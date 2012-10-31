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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import at.devstar.mytab.datastore.TubeLoader;
import at.devstar.mytab.logic.PreferencesManager;

public class MainActivity extends Activity {
	private final static String URI = "at.devstar.mytab.MainActivity";
	private static TubeLoader loader = null;
	private LinearLayout content = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    	this.buildLayout();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	this.buildLayout();
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
    		case R.id.options_account:
    			intent = new Intent(this, OptionsActivities.AccountActivity.class);
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
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	//TODO implement context menu for ThumbnailViews => call registerForContextMenu first
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	//TODO implement context menu for ThumbnailViews
    	return false;
    }
    
    private void buildLayout() {
    	if(PreferencesManager.isReloadNeeded()) {
    		Log.d(URI, "reloading");
    		MainActivity.loader.clear();
        	Intent i = new Intent(this, StartActivity.class);
        	startActivity(i);
    	} else {
    		if(isLoaded()) {
		        try {
		    		this.content = (LinearLayout) findViewById(R.id.appContent);
		            loader.insertTubeViewsInLayout(this.content);
		        } catch(IllegalStateException e) {
		        	Log.e(URI, "illegal state: "+e.getMessage());
		        } catch(NullPointerException e) {
		        	if(loader == null) Log.e(URI, "TubeLoader not initialized: "+e.getMessage());
		        	if(content == null) Log.e(URI, "appContent not found: "+e.getMessage());
			    } catch(ClassCastException e) {
			    	Log.e(URI, "class cast failed: "+e.getMessage());
			    }
    		} else {
        		Log.d(URI, "loading");
            	Intent i = new Intent(this, StartActivity.class);
            	startActivity(i);
    		}
    	}
    }
    
    public static boolean isLoaded() {
    	boolean r;
    	if(loader == null) {
    		r = false;
    	} else {
    		r = !loader.isEmpty();
    	}
    	return r;
    }
    
    public static TubeLoader getTubeLoader() {
    	return loader;
    }
    
    public static void setTubeLoader(TubeLoader loader) {
    	MainActivity.loader = loader;
    }
}