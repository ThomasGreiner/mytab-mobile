package at.devstar.mytab.sources.test;

import at.devstar.mytab.enums.SourceEnum;
import at.devstar.mytab.sources.YouTubeSource;
import junit.framework.TestCase;

public class YouTubeSourceTest extends TestCase {
	private YouTubeSource yt;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testYouTubeSource() {
		yt = new YouTubeSource(SourceEnum.TRENDING);
		yt = new YouTubeSource(SourceEnum.TOP_RATED);
		yt = new YouTubeSource(SourceEnum.MOST_POPULAR);
		yt = new YouTubeSource(SourceEnum.MOST_VIEWED);
	}

	public void testGetSource() {
		fail("Not yet implemented");
	}

	public void testGetAllVideoElements() {
		fail("Not yet implemented");
	}
}
