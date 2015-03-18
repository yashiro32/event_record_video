package org.red5.core;

import org.red5.server.api.IScope; 
import org.red5.server.api.stream.IStreamFilenameGenerator; 

public class CustomFilenameGenerator implements IStreamFilenameGenerator { 
	/** Path that will store recorded videos. */ 
    public String recordPath = "/var/www/html/events/" + Application.event_id + "/"; 
    /** Path that contains VOD streams. */ 
    public String playbackPath = "/var/www/html/events/" + Application.event_id + "/"; 
    /** Set if the path is absolute or relative */ 
    // public boolean resolvesAbsolutePath = false; 
    /** If you want to use Absolute Path, set VARIABLE "resolvesAbsolutePath" to "true" */
    public boolean resolvesAbsolutePath = true; 
    
    public String generateFilename(IScope scope, String name, GenerationType type) { 
    	// recordPath = "/var/www/html/events/";
    	// playbackPath = "/var/www/html/events/";
    	
    	// Generate filename without an extension. 
        return generateFilename(scope, name, null, type); 
    }
    
    public String generateFilename(IScope scope, String name, String extension, GenerationType type) { 
    	String filename; 
        if (type == GenerationType.RECORD) { 
        	filename = recordPath + name; 
        } else { 
        	filename = playbackPath + name; 
        }
   
        if (extension != null) { 
        	// Add extension 
            filename += extension; 
        }
  
        return filename; 
    } 
 
    public boolean resolvesToAbsolutePath() { 
    	return resolvesAbsolutePath; 
    } 
} 