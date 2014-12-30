/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables.interfaces;


import com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.JsonNode;
import org.jooq.test.all.types.JSONJacksonHelloWorld;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface ITExoticTypes extends Serializable {

	/**
	 * Getter for <code>public.t_exotic_types.id</code>.
	 */
	public Integer getId();

	/**
	 * Getter for <code>public.t_exotic_types.uu</code>.
	 */
	public UUID getUu();

	/**
	 * Getter for <code>public.t_exotic_types.js</code>.
	 */
	public Object getJs();

	/**
	 * Getter for <code>public.t_exotic_types.js_gson</code>.
	 */
	public JsonElement getJsGson();

	/**
	 * Getter for <code>public.t_exotic_types.js_javax</code>.
	 */
	public Object getJsJavax();

	/**
	 * Getter for <code>public.t_exotic_types.js_jackson</code>.
	 */
	public JSONJacksonHelloWorld getJsJackson();

	/**
	 * Getter for <code>public.t_exotic_types.js_jackson_json_node</code>.
	 */
	public JsonNode getJsJacksonJsonNode();

	/**
	 * Getter for <code>public.t_exotic_types.hstore</code>.
	 */
	public Object getHstore();

	/**
	 * Getter for <code>public.t_exotic_types.hstore_map</code>.
	 */
	public Map<String, String> getHstoreMap();
}
