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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class HTTPManager {
	public final static String URI = "at.devstar.mytab.HTTPManager";
	
	public static void sendRequest(String url, DefaultHandler handler) {
		try {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser sax = factory.newSAXParser();
	        XMLReader parser = sax.getXMLReader();
	        
	        parser.setContentHandler(handler);
	        
	        InputSource is = new InputSource(new URL(url).openStream());
	        is.setEncoding("UTF-8");
	        parser.parse(is);
			parser = null;
		} catch(SAXException e) {
			Log.e(URI, "error parsing data: "+e.getMessage());
		} catch(ParserConfigurationException e) {
			Log.e(URI, "problem with parser configuration: "+e.getMessage());
		} catch(MalformedURLException e) {
			Log.e(URI, "malformed URL: "+e.getMessage());
		} catch(IOException e) {
			Log.e(URI, "error fetching data: "+e.getMessage());
		}
	}
	
	public static Drawable getDrawableFromURL(String url) {
		URL u;
		Object content = null;
		try {
			u = new URL(url);
			content = (Object) u.getContent();
		} catch (IOException e) {
			Log.e(URI, "error fetching data: "+e.getMessage());
		}
		InputStream is = (InputStream) content;
		return Drawable.createFromStream(is, "src");
	}
}