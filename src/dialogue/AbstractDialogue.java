package dialogue;

import java.io.File;
import java.util.ArrayList;

import com.golden.gamedev.util.FileUtil;

public abstract class AbstractDialogue {
	
	public abstract void goToNextLine(boolean choice);
	
	public abstract String getCurrentLine();
	
	public abstract boolean isDone();
	
	String[] readFile(String url){
		String[] lines =  FileUtil.fileRead(new File(url));
		return lines;
	}

}
