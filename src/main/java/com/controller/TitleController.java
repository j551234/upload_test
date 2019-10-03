package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Titles;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.JsonUtils;

@Controller
public class TitleController {
	@Autowired
	private ObjectMapper mapper;
	String path =  "./theme/title.json";


	@RequestMapping("/title")
	@ResponseBody
	public String getTitle() {
		String jsonStr = JsonUtils.readJsonFile(path);
		return jsonStr;

	}


		


	@PostMapping("/title/updateTitle/{title}/{subtitle}")
	@ResponseBody
	public String updateTitle(@PathVariable("title") String title, @PathVariable("subtitle") String subtitle) {
		try {
			String jsonStr = JsonUtils.readJsonFile(path);
			Titles titleobj = mapper.readValue(jsonStr, Titles.class);
			titleobj.setTitle(title);
			titleobj.setSubtitle(subtitle);
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(titleobj);
			JsonUtils.writeJsonFile(jsonString, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtils.readJsonFile(path);
	}

}
