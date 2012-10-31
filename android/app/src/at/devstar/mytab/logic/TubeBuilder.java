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

package at.devstar.mytab.logic;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import at.devstar.mytab.MainActivity;
import at.devstar.mytab.datastore.TubeLoader;
import at.devstar.mytab.interfaces.ITube;
import at.devstar.mytab.views.TubeView;

public class TubeBuilder implements Runnable {
	private final static String URI = "at.devstar.mytab.TubeBuilder";
	protected Activity activity;
	
	public TubeBuilder(Activity activity) {
		this.activity = activity;
	}
	
	public void run() {
		Log.d(URI, "thread started");
		
		if(!MainActivity.isLoaded()) {
			TubeLoader loader = MainActivity.getTubeLoader();
			if(loader == null) {
				loader = new TubeLoader();
				MainActivity.setTubeLoader(loader);
			}
			
			SourceManager.initializeSources();
			List<ITube> tubes = SourceManager.getAllTubes();
			List<Thread> threads = new ArrayList<Thread>();
			
			for(ITube t : tubes) {
				Thread th = new Thread(new TubeBuilder.TubeViewBuilder(this.activity, t, loader));
				th.start();
				threads.add(th);
			}
			for(Thread t : threads) {
				try {
					t.join(); //TODO wrong TubeView order!
				} catch (InterruptedException e) {
					//do nothing
					Log.w(URI, "thread interrupted: "+e.getMessage());
				}
			}
		}
		
		this.activity.finish();
		
		Log.d(URI, "start MainActivity");
		Intent intent = new Intent(this.activity, MainActivity.class);
		this.activity.startActivity(intent);
		
		Log.d(URI, "thread finished");
	}
	
	private static class TubeViewBuilder implements Runnable {
		private Activity activity;
		private ITube tube;
		private TubeLoader loader;
		
		public TubeViewBuilder(Activity activity, ITube tube, TubeLoader loader) {
			this.activity = activity;
			this.tube = tube;
			this.loader = loader;
		}

		public void run() {
			TubeView tube = new TubeView(this.activity, this.tube);
			this.loader.addTubeView(tube);
		}
	}
}
