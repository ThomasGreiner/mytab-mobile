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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import at.devstar.mytab.objects.VideoElement;

public class YouTubeParser extends DefaultHandler {
	/*
	1	entry
	2		title
	2		media:group
	3			yt:videoid
	*/
	private YouTubeSource source;
	private int depth = 0;
	private String tag;
	private VideoElement video;
	
	public YouTubeParser(YouTubeSource source) {
		this.source = source;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.tag = localName;
		
		switch(this.depth++) {
			case 1:
				//entry
				if(this.tag.equals("entry")) {
					this.video = new VideoElement();
					this.source.addVideoElement(this.video);
				}
				break;
			case 2:
				//title, media:group
				break;
			case 3:
				//yt:videoid
				break;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String text = new String(ch, start, length);
		
		switch(this.depth-1) {
			case 1:
				//entry
				break;
			case 2:
				//title, media:group
				if(this.tag.equals("title")) {
					this.video.setTitle(text);
				}
				break;
			case 3:
				//yt:videoid
				if(this.tag.equals("videoid")) {
					this.video.setVid(text);
				}
				break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.depth--;
	}
}
