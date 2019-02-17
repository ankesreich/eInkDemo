package de.ankesreich;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.spi.SpiChannel;

import de.ankesreich.displaydriver.RaspiConfiguration;
import de.ankesreich.displaydriver.fourtwo.Epd4in2;


@Component
public class DisplayFactory {

	Logger log = Logger.getLogger(DisplayFactory.class.getName());	
	
	@Value("${useRealDisplays}")
    private String useRealDisplays;

	@Bean
	public Epd4in2 createDisplay() throws IOException
	{
		log.info("createDisplay (This method should only be called once useRealdDiplays "  + useRealDisplays +  " )");
		if (Boolean.parseBoolean(useRealDisplays)) {
			RaspiConfiguration config = new RaspiConfiguration(RaspiBcmPin.GPIO_21, RaspiBcmPin.GPIO_16,
					RaspiBcmPin.GPIO_23, RaspiBcmPin.GPIO_07, SpiChannel.CS1);
			Epd4in2 epd4in2 = new Epd4in2();
			epd4in2.init(config);
			epd4in2.sleep();
			return epd4in2;
		}
		return  new Epd4in2();
	}
	
	
}
