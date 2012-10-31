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

package at.devstar.mytab.sources;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.util.Log;
import at.devstar.mytab.enums.SourceEnum;
import at.devstar.mytab.interfaces.ISource;
import at.devstar.mytab.logic.HTTPManager;
import at.devstar.mytab.logic.PreferencesManager;
import at.devstar.mytab.objects.VideoElement;

public class YouTubeSource implements ISource {
	private final static String URI = "at.devstar.mytab.YouTubeSource";
	private SourceEnum source;
	private ArrayList<VideoElement> videos = new ArrayList<VideoElement>();
	
	public YouTubeSource(SourceEnum source) {
		this.source = source;
		StringBuilder urlString = new StringBuilder();
		
		URL url = null;
		switch(source) {
			case FAVORITES:
			case SUBSCRIPTIONS:
				if(PreferencesManager.getYouTubeUsername() != null && PreferencesManager.getYouTubeUsername() != "") {
					urlString.append("http://gdata.youtube.com/feeds/api/users/");
					urlString.append(PreferencesManager.getYouTubeUsername());
					urlString.append("/");
					urlString.append(source.getId());
					urlString.append("?max-results=10&v=2&format=5&fields=entry(title,media:group(yt:videoid))");
					urlString.append((source != SourceEnum.FAVORITES)?"&time=this_week":"");
				}
				break;
			case TRENDING:
			case TOP_RATED:
			case MOST_POPULAR:
			case MOST_VIEWED:
				urlString.append("http://gdata.youtube.com/feeds/api/standardfeeds/");
				urlString.append(source.getId());
				urlString.append("?max-results=10&v=2&format=5&fields=entry(title,media:group(yt:videoid))");
				urlString.append((source != SourceEnum.TRENDING)?"&time=today":"");
				break;
			default:
				Log.e(URI, "source not found");
		}
		
		if(urlString.length() > 0) {
			try {
				url = new URL(urlString.toString());
				HTTPManager.sendRequest(
					url.toString(),
					new YouTubeParser(this)
				);
			} catch (MalformedURLException e) {
				Log.e(URI, "malformed URL: "+e.getMessage());
			}
		}
	}
	
	public boolean isEmpty() {
		return (this.videos.size() == 0);
	}
	
	public SourceEnum getSource() {
		return this.source;
	}

	public ArrayList<VideoElement> getAllVideoElements() {
		return this.videos;
	}
	
	public void addVideoElement(VideoElement video) {
		this.videos.add(video);
	}
}
