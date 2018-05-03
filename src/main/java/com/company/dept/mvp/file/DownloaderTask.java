package com.company.dept.mvp.file;

import java.util.concurrent.Callable;

import static com.company.dept.mvp.common.AppConstants.*;

public class DownloaderTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		String cmd = _WGET_LOCATION + _SPACE + getFilePath();
		Process process = Runtime.getRuntime().exec(cmd);  // Sharing runtime (to create multiple processes), it can be replaced by (third party) tool/service
		return "Processing - " + this.getFilePath() + " on " + process.waitFor();  // waitfor will hold and waiting for to finish
	}

	public String getFilePath() {
		return filePath;
	}
	
	public DownloaderTask(String filePath, String wgetLocation) {
		super();
		this.filePath = filePath;
		this._WGET_LOCATION = wgetLocation;
	}

	public DownloaderTask(String filePath) {
		super();
		this.filePath = filePath;
	}
	
	private String filePath;
	private String _WGET_LOCATION = WGET_LOCATION;
	private String _SPACE = SPACE;
}
