package org.red5.core;

/*
 * RED5 Open Source Flash Server - http://www.osflash.org/red5
 * 
 * Copyright (c) 2006-2008 by respective authors (see below). All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU Lesser General Public License as published by the Free Software 
 * Foundation; either version 2.1 of the License, or (at your option) any later 
 * version. 
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along 
 * with this library; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 */

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.service.ServiceUtils;

import org.red5.server.api.stream.IStreamFilenameGenerator; 
import org.red5.server.api.stream.IStreamFilenameGenerator.GenerationType;

/**
 * Sample application that uses the client manager.
 * 
 * @author The Red5 Project (red5@osflash.org)
 */
public class Application extends ApplicationAdapter implements IStreamFilenameGenerator {
	public static String event_id;
	
	/** Path that will store recorded videos. */ 
    public String recordPath; 
    /** Path that contains VOD streams. */ 
    public String playbackPath; 
    /** Set if the path is absolute or relative */ 
    // public boolean resolvesAbsolutePath = false; 
    /** If you want to use Absolute Path, set VARIABLE "resolvesAbsolutePath" to "true" */
    public boolean resolvesAbsolutePath = true; 

	/** {@inheritDoc} */
    @Override
	public boolean connect(IConnection conn, IScope scope, Object[] params) {
    	// event_id = "AG76227622";
    	event_id = params[0].toString();
    	recordPath = "/var/www/html/events/" + event_id + "/"; 
    	playbackPath = "/var/www/html/events/" + event_id + "/"; 
		return true;
	}

	/** {@inheritDoc} */
    @Override
	public void disconnect(IConnection conn, IScope scope) {
		super.disconnect(conn, scope);
	}
    
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
