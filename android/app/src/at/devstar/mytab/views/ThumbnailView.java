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

package at.devstar.mytab.views;

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import at.devstar.mytab.logic.HTTPManager;
import at.devstar.mytab.objects.VideoElement;

public class ThumbnailView extends LinearLayout implements OnClickListener {
	private final static String URI = "at.devstar.mytab.ThumbnailView";
	private Context context;
	private VideoElement video;
	
	public ThumbnailView(Context context, VideoElement video) {
		super(context);
		this.setOrientation(HORIZONTAL);
		this.context = context;
		this.video = video;
		
		ImageView img = new ImageView(context);
		img.setImageDrawable(HTTPManager.getDrawableFromURL(this.video.getThumbnail()));
		img.setLayoutParams(new LayoutParams(130, 100));
		img.setPadding(0, 0, 0, 5);
		//TODO rounded corners for thumbnails
		img.setOnClickListener(this);
		
		this.addView(img);
	}

	public void onClick(View v) {
		//TODO embed own player?
		try {
			URL url = new URL("http://www.youtube.com/watch?v="+this.video.getVid());
			Intent intent = new Intent(
				Intent.ACTION_VIEW,
				Uri.parse(url.toString())
			);
			this.context.startActivity(intent);
		}
		catch(MalformedURLException e) {
			Log.e(URI, "malformed url: "+e.getMessage());
		}
	}
}