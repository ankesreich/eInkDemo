package de.ankesreich;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.ankesreich.displaydriver.fourtwo.Epd4in2;
import de.ankesreich.imagegeneration.ImageGenerator;
import de.ankesreich.persistence.Message;
import de.ankesreich.persistence.MessageRepository;

@Service
public class DisplayService {

	@Autowired
	MessageRepository messageRepository;

	
	@Autowired
	Epd4in2 display;
	
	public void saveMessageInDb(String author, String text) {
		Message message = new Message(author, new Date(), text);
		messageRepository.saveAndFlush(message);
	}


	public Message getLastMessage() {
		 List<Message> messages = messageRepository.findAll(Sort.by("datumCreation").descending());
		 if(messages.size() > 0)
		 {
			 return messages.get(0);
		 }
		 return null;
	}


	public void refreshDisplay() throws IOException {
		
		Message lastMessage = getLastMessage();
		String text = null;
		if(lastMessage != null)
		{
			text = lastMessage.getMessage();
		}
		String fileName = new Date().getTime() + "gui";
		File tmpFile = null;
		try
		{
			display.wakeUp();
			display.clearFrames();
			tmpFile = ImageGenerator.writeImageToFile(fileName, text);
			display.displayImageFromFile(tmpFile.getAbsolutePath(), true);
		}
		finally
		{
			display.sleep();
			if(tmpFile != null && tmpFile.exists())
			{
				tmpFile.delete();
			}
		}
		
	}


}
