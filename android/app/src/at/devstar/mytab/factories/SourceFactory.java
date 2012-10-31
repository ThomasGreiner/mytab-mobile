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

package at.devstar.mytab.factories;

import android.util.Log;
import at.devstar.mytab.enums.SourceEnum;
import at.devstar.mytab.interfaces.ISource;
import at.devstar.mytab.logic.PreferencesManager;
import at.devstar.mytab.sources.YouTubeSource;

public class SourceFactory {
	private final static String URI = "at.devstar.mytab.SourceFactory";
	
	public static ISource getSource(SourceEnum source) {
		ISource s = null;
		
		if(PreferencesManager.showFeed(source)) {
			switch(source) {
				case GOOGLE_READER:
					//s = new MockSource(1); //TODO: replace MockSource
					break;
				case FAVORITES:
					s = new YouTubeSource(SourceEnum.FAVORITES);
					break;
				case TRENDING:
					s = new YouTubeSource(SourceEnum.TRENDING);
					break;
				case TOP_RATED:
					s = new YouTubeSource(SourceEnum.TOP_RATED);
					break;
				case MOST_POPULAR:
					s = new YouTubeSource(SourceEnum.MOST_POPULAR);
					break;
				case MOST_VIEWED:
					s = new YouTubeSource(SourceEnum.MOST_VIEWED);
					break;
				case SUBSCRIPTIONS:
					s = new YouTubeSource(SourceEnum.SUBSCRIPTIONS);
					break;
				default:
					Log.e(URI, "source not found: "+source);
			}
		}
		
		if(s != null) {
			if(s.isEmpty()) {
				s = null;
			}
		}
		return s;
	}
}
