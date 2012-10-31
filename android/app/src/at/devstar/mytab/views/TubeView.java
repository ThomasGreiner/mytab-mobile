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

import java.util.ArrayList;
import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import at.devstar.mytab.interfaces.ITube;
import at.devstar.mytab.objects.VideoElement;

public class TubeView extends LinearLayout {
	public TubeView(Context context, ITube tube) {
		super(context);
		this.setOrientation(VERTICAL);
		
		//create tube logo
		ImageView logo = new ImageView(context);
		LayoutParams params = new LayoutParams(86,54);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		logo.setLayoutParams(params);
		logo.setBackgroundColor(0xFFFFFFFF);
		logo.setImageResource(tube.getSource().getIconResource());
		this.addView(logo);
		
		ScrollView scroller = new ScrollView(context); //alternatively: HorizontalScrollView
		scroller.setFillViewport(true);
		scroller.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
		scroller.setBackgroundColor(0xFFFFFFFF); //0x00(ALPHA)00(RED)00(GREEN)00(BLUE)
		scroller.setScrollbarFadingEnabled(true);
		this.addView(scroller);
		
			LinearLayout scroll = new LinearLayout(context);
			scroll.setOrientation(VERTICAL);
			scroll.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
			scroll.setPadding(3, 5, 3, 0);
			scroller.addView(scroll);
			
				//create thumbnails
				ArrayList<VideoElement> videos = tube.getAllVideoElements();
				for(VideoElement v : videos) {
					ThumbnailView thumb = new ThumbnailView(context, v);
					scroll.addView(thumb);
				}
	}
}