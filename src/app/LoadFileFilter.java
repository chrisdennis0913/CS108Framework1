package app;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class LoadFileFilter extends FileFilter {
	private final String[] acceptedFileExts;
	
	public LoadFileFilter(String[] acceptedFileExts){
		this.acceptedFileExts = acceptedFileExts;
	}

	@Override
	public boolean accept(File file) {
		for (String extension : acceptedFileExts) {
			if (file.getName().toLowerCase().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}