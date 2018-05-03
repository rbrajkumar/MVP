package com.company.dept.mvp;

import static com.company.dept.mvp.common.AppConstants.FINISHED;
import static com.company.dept.mvp.common.AppConstants.PROGRESS;
import static com.company.dept.mvp.common.Utils.extractFileName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.dept.mvp.file.DownloaderTask;
import com.company.dept.mvp.model.FileParam;

@RestController
@RequestMapping("/download")
public class DownloadController {
	
	public List<String> files = new ArrayList<String>();
	
	@PostMapping(value = "/files")
	@ResponseStatus(HttpStatus.CREATED)
	public void process(@RequestBody FileParam filesParam) {
		List<String> paths = filesParam.getPath();
		setFiles(paths);
		if(this.files.isEmpty()) return;  // or escalate RuntimeExcpn thru app framework.
		
		String fName = "";
		executor = Executors.newFixedThreadPool(tCount); // As per req for each. we can use cached pool as well for performance reason
		for(String url: files) {
			fName = extractFileName(url);
			Future<String> future = executor.submit(new DownloaderTask(url, wgetLoc));
			statusMap.put(fName, future);
		}
	}
	
	@GetMapping("/status")
	public List<String> status() throws InterruptedException, ExecutionException {
		List<String> result = new ArrayList<String>();
		for(Map.Entry<String, Future<String>> entry : statusMap.entrySet()) {
			String stats = "NA";
			if(null != entry.getValue()) {
				stats = entry.getValue().isDone() ? FINISHED : PROGRESS;
			}
			result.add("For file name " + entry.getKey() + " status is " + stats );
			System.out.println("For file name " + entry.getKey() + " status is " + stats);  // just debug purpose...use logger
		}
		
		return result; // contains all task status
	}
	
	/**
	 * Just for more user friendly purpose, to use the 
	 * same file name as task id, Instead of creating unique id and maintained in map.
	 * 
	 * Note : for demo purpose we assume file name is unique***
	 * 
	 * @param fileName
	 * @return specific file status
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@GetMapping("/{fileName}/status")
	public String status(@PathVariable String fileName) throws InterruptedException, ExecutionException {
		String result = "NA";
		Future<String> status = statusMap.get(fileName);
		if(null != status) {
			result = status.isDone() ? FINISHED : PROGRESS;
		}
		return result; // contains specific file task status
	}
	
	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	@Value("${mvp.app.download.thread.count}")
	private int tCount;
	
	@Value("${mvp.app.download.wget.location}")
	private String wgetLoc;

	private ExecutorService executor = null;  
	private Map<String, Future<String>> statusMap = new ConcurrentHashMap<String, Future<String>>();// Assume that any given time one set of batch only got processed.
																								// with unique file name.
}
