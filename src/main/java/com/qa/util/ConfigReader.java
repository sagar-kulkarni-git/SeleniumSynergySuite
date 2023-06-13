package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* We will be reading this with properties file
 * 
*/
public class ConfigReader {

	private Properties prop;

	/*
	 * Now, if someone wants to get the prop, we can not access that directly, to
	 * access that We are creating the following method, this is an encapsulation We
	 * are returning prop hence instead of void we are Properties as prop is
	 * properties type
	 * To read the multiple properties we can give the path inside init_prop()
	 */

	public Properties init_prop() {
		prop = new Properties();

		/*
		 * Now, we have to interact with config.properties so we have to use
		 * FileInputStream Class; import from java.io Where our configuration properties
		 * file is available that we need to add path of that under the constructor.
		 * When we add the path of config.properties, it will ask to add the code in try
		 * catch block
		 */
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");

			/*
			 * Load the properties file with the help of properties class object, we write
			 * prop.load(ip), it will ask to surround with try catch block, select add catch
			 * surrounding to try
			 */

			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// this prop is having all the properties available over there
		return prop;

	}

}
