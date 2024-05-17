package application;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/**
 * @author AIT MEDDOUR Nadjim
 * The WordsExtraction class is responsible for extracting words from the specified URL,
 * specifically targeting the content within <a> that are inside  <p> HTML tags using regular expressions.
 */

public class WordsExtraction {
	/**
     * Extracts words from the web page by connecting to the specified URL,
     * retrieving and parsing the HTML content, and applying regular expressions
     * to extract text within <a> that are inside  <p> tags.
     *
     * @return A list of extracted words from the web page.
     */
	
	public static List<String> extractWords() {
		String urlStr = "https://fr.wiktionary.org/wiki/Wiktionnaire:Liste_de_1750_mots_fran%C3%A7ais_les_plus_courants";
        List<String> extractedTexts = new ArrayList<>();
        
        try {
        	
        	// Step 1: Create a URL for the web page and establish an HTTP connection
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

         
            // Step 2: Read the content of the web page
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            inputStream.close();
            connection.disconnect();

            
            // Step 3: Use regular expressions to extract content from <p> tags
            String htmlContent = content.toString();
            Pattern pattern = Pattern.compile("<p[^>]*>(.*?)</p>");
            Matcher matcher = pattern.matcher(htmlContent);

            while (matcher.find()) {
                String paragraphContent = matcher.group(1);

               
                // Step 4: Use another regular expression to extract text from <a> tags inside the paragraph
                Pattern linkPattern = Pattern.compile("<a[^>]*>(.*?)</a>");
                Matcher linkMatcher = linkPattern.matcher(paragraphContent);
                while (linkMatcher.find()) {
                    String linkText = linkMatcher.group(1);

                    // Add the extracted text to the list                    
                    extractedTexts.add(linkText);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return extractedTexts;
    }

}
