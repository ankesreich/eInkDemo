package de.ankesreich.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ankesreich.DisplayService;
import de.ankesreich.persistence.Message;
import io.swagger.annotations.ApiOperation;


@RestController
public class RestApi {

	Logger log = Logger.getLogger(RestApi.class.getName());

	@Value("${env}")
    private String env;
	
	@Autowired
	DisplayService displayService;
	
	@ApiOperation(value = "Shows the environment profile [local | raspi]")
	@GetMapping("/env")
	String showEnv() {
		String msg = "profile " + env;
		log.info( msg);
		return msg;
	}
	
	@ApiOperation(value = "Stores the message in the database.")
	@PostMapping("/addMessage")
	void addMessage(
			@RequestParam(required=true) String author, 
			@RequestParam(required=true) String text
			) {
		log.info( "addMessage " + text + " from " + author );
		if(text == null || text.length() > 60)
		{
			throw new RuntimeException("Text is mandatory and must not be longer than 60 characters");
		}
		try
		{
			displayService.saveMessageInDb(author, text);
		}
		catch(Exception e)
		{
			log.log(Level.WARNING, "Exception addMessage", e);
			throw new RuntimeException(e);
		}
	}
	
	@ApiOperation(value = "Shows last message")
	@GetMapping("/getLastMessage")
	String getMessage() {
		try
		{
			Message message = displayService.getLastMessage();
			log.info( "getMessage " + message);
			return (message != null) ? message.toString() : "not message available";
		}
		catch(Exception e)
		{
			log.log(Level.WARNING, "Exception getLastMessage", e);
			throw new RuntimeException(e);
		}
	}
	
	@ApiOperation(value = "Refresh of the display with the last message of the database.")
	@PutMapping("/refreshDisplay")
	void refreshDisplay() {
		try
		{
			log.info( "refreshDisplay ");
			displayService.refreshDisplay();
		}
		catch(Exception e)
		{
			log.log(Level.WARNING, "Exception refreshDisplay", e);
			throw new RuntimeException(e);
		}
	}


	

	
}
