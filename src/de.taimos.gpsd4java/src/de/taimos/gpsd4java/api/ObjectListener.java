/**
 * Copyright 2011 Thorsten Höger, Taimos GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.taimos.gpsd4java.api;

import de.taimos.gpsd4java.types.DeviceObject;
import de.taimos.gpsd4java.types.DevicesObject;
import de.taimos.gpsd4java.types.SKYObject;
import de.taimos.gpsd4java.types.TPVObject;

/**
 * Adapter class for {@link IObjectListener}
 * 
 * created: 18.01.2011
 */
public class ObjectListener implements IObjectListener {
	
	@Override
	public void handleTPV(TPVObject tpv) {
		// implement in subclass if needed
	}
	
	@Override
	public void handleSKY(SKYObject sky) {
		// implement in subclass if needed
	}
	
	@Override
	public void handleATT(TPVObject att) {
		// implement in subclass if needed
	}
	
	@Override
	public void handleDevices(DevicesObject devices) {
		// implement in subclass if needed
	}
	
	@Override
	public void handleDevice(DeviceObject device) {
		// implement in subclass if needed
	}
	
}