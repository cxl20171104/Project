package com.alphasta.controller.work.statistics;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("arcgis")
public class ArcgisController {

	

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/arcgis";
    }
	
}
