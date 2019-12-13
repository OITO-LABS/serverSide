
package com.asset.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping("oito-trv/**")//handle buyer ui refresh
	public String index() {
		return "forward:/";
	}
}

