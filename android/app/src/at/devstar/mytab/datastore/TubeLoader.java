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

package at.devstar.mytab.datastore;

import java.util.ArrayList;
import android.widget.LinearLayout;
import at.devstar.mytab.views.TubeView;

public class TubeLoader {
	private LinearLayout layout = null;
	private ArrayList<TubeView> tubes;
	
	public TubeLoader() {
		this.tubes = new ArrayList<TubeView>();
	}
	
	public void addTubeView(TubeView tube) {
		this.tubes.add(tube);
	}
	
	public void clear() {
		this.tubes.clear();
	}
	
	public void insertTubeViewsInLayout(LinearLayout layout) {
		if(this.layout != null) {
			this.layout.removeAllViews();
		}
		this.layout = layout;
		
		for(TubeView t : this.tubes) {
			this.layout.addView(t);
		}
	}
	
	public boolean isEmpty() {
		return this.tubes.isEmpty();
	}
}