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

package at.devstar.mytab.enums;

import at.devstar.mytab.R;

public enum SourceEnum {
	GOOGLE_READER(
		"reader",
		R.drawable.reader
	),
	FAVORITES(
		"favorites",
		R.drawable.favorites
	),
	TRENDING(
		"on_the_web",
		R.drawable.trending
	),
	TOP_RATED(
		"top_rated",
		R.drawable.rated
	),
	MOST_POPULAR(
		"most_popular",
		R.drawable.popular
	),
	MOST_VIEWED(
		"most_viewed",
		R.drawable.viewed
	),
	SUBSCRIPTIONS(
		"newsubscriptionvideos",
		R.drawable.subscriptions
	),
	MOCK(
		"mock",
		R.drawable.reader
	);
	
	private String id;
	private int icon;
	
	private SourceEnum(String id, int icon) {
		this.id = id;
		this.icon = icon;
	}
	
	public String getId() {
		return this.id;
	}
	
	public int getIconResource() {
		return this.icon;
	}
}
