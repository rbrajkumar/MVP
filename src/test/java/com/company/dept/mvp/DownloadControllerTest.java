package com.company.dept.mvp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static com.company.dept.mvp.common.Utils.asJsonString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.company.dept.mvp.model.FileParam;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DownloadControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void fileProcessShouldReturnOK() throws Exception {
		// creating mock utils
		FileParam mock = new FileParam();
		mock.setPath(fillUrls());
		// calling rest service
		this.mockMvc.perform(post("/download/files")
			  .content(asJsonString(mock))
      		  .contentType(MediaType.APPLICATION_JSON)
      		  .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is2xxSuccessful());
    }
	
	private List<String> fillUrls() {
		/*String[] filesArr = {  // for smaller files for testing
				"https://github.com/Homebrew/brew/blob/master/CHANGELOG.md",
				"https://github.com/Homebrew/brew/blob/master/README.md",
				"https://github.com/Homebrew/brew/blob/master/LICENSE.txt",
				"https://github.com/Homebrew/brew/blob/master/CONTRIBUTING.md",
				"https://github.com/Homebrew/brew/blob/master/CODE_OF_CONDUCT.md"
		};*/
		
		String[] filesArr2 = {
				"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_10mb.mp4",
				"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_20mb.mp4",
				"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_30mb.mp4",
				"http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_30mb.mp4",
				"http://www.sample-videos.com/video/mp4/360/big_buck_bunny_360p_30mb.mp4",
				"http://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4",
				"http://www.sample-videos.com/video/3gp/240/big_buck_bunny_240p_30mb.3gp",
				"http://www.sample-videos.com/video/mkv/240/big_buck_bunny_240p_30mb.mkv"
		};
				
		return Arrays.asList(filesArr2);
	}
	
	// Due to time constraints all use cases given as curl - use them in your testing shell script.

}
