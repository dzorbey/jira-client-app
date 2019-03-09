/*
 * Service Copyright (C) 2015 Nishimura Software Studio
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.app.client;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import com.google.api.client.http.HttpResponse;

public abstract class Service {

  public abstract URI getEndpoint(String path) throws IOException;

  public abstract HttpResponse executeURI(URI endpoint) throws IOException, InterruptedException;

  public abstract HttpResponse executeURIHeader(URI endpoint,String header) throws IOException, InterruptedException;
  
  public abstract HttpResponse executeURIHeader(URI endpoint, Map<String, String> headers) throws IOException, InterruptedException;
}
